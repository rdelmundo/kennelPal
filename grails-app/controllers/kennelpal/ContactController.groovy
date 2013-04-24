package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class ContactController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // default action, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: contact/list.gsp
    // contactInstanceList is a map of all the contacts in the model
    // contactInstanceTotal is the total number of contacts in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [contactInstanceList: Contact.list(params), contactInstanceTotal: Contact.count()]
    }

    // Creates and saves to database new instance of contact
    def create() {
        [contactInstance: new Contact(params)]
    }

    // Saves instance of contact to database
    // Returns to the contact/create.gsp view if save is not successful
    // If save is successful, redirects to contact/show.gsp view and shows newly created contact
    def save() {
        def contactInstance = new Contact(params)
        if (!contactInstance.save(flush: true)) {
            render(view: "create", model: [contactInstance: contactInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contact.label', default: 'Contact'), contactInstance.id])
        redirect(action: "show", id: contactInstance.id)
    }

    // Shows the contact instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the contact instance is returned
    // Else an error message is shown and they are redirected to the contact/list.gsp
    def show(Long id) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "list")
            return
        }

        [contactInstance: contactInstance]
    }

    // Returns contact instance at primary key supplied as argument
    // If the primary key is invalid, the contact/list.gsp view is loaded
    // Else the contact instance at the specified primary key is returned
    def edit(Long id) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "list")
            return
        }

        [contactInstance: contactInstance]
    }

    // Updates the contact instance at the primary key supplied as an argument
    // Compares the version of the existing contact instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the contact instance at the specified primary key is updated
    def update(Long id, Long version) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (contactInstance.version > version) {
                contactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contact.label', default: 'Contact')] as Object[],
                          "Another user has updated this Contact while you were editing")
                render(view: "edit", model: [contactInstance: contactInstance])
                return
            }
        }

        contactInstance.properties = params

        if (!contactInstance.save(flush: true)) {
            render(view: "edit", model: [contactInstance: contactInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'contact.label', default: 'Contact'), contactInstance.id])
        redirect(action: "show", id: contactInstance.id)
    }

    // Deletes the specified contact instance
    // The argument is the primary key of the contact instance to delete
    def delete(Long id) {
        def contactInstance = Contact.get(id)
        if (!contactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "list")
            return
        }

        try {
            contactInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), id])
            redirect(action: "show", id: id)
        }
    }
}

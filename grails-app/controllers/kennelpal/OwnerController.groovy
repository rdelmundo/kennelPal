package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class OwnerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // default, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: owner/list.gsp
    // ownerInstanceList is a map of all the owners in the model
    // ownerInstanceTotal is the total number of owners in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ownerInstanceList: Owner.list(params), ownerInstanceTotal: Owner.count()]
    }

    // Creates and saves to database new instance of owner
    def create() {
        [ownerInstance: new Owner(params)]
    }

    // Saves instance of owner to database
    // Returns to the owner/create.gsp view if save is not successful
    // If save is successful, redirects to owner/show.gsp view and shows newly created owner
    def save() {
        def ownerInstance = new Owner(params)
        if (!ownerInstance.save(flush: true)) {
            render(view: "create", model: [ownerInstance: ownerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'owner.label', default: 'Owner'), ownerInstance.id])
        redirect(action: "show", id: ownerInstance.id)
    }

    // Shows the owner instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the owner instance is returned
    // Else an error message is shown and they are redirected to the owner/list.gsp
    def show(Long id) {
        def ownerInstance = Owner.get(id)
        if (!ownerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
            return
        }

        [ownerInstance: ownerInstance]
    }

    // Returns owner instance at primary key supplied as argument
    // If the primary key is invalid, the owner/list.gsp view is loaded
    // Else the owner instance at the specified primary key is returned
    def edit(Long id) {
        def ownerInstance = Owner.get(id)
        if (!ownerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
            return
        }

        [ownerInstance: ownerInstance]
    }

    // Updates the owner instance at the primary key supplied as an argument
    // Compares the version of the existing owner instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the owner instance at the specified primary key is updated
    def update(Long id, Long version) {
        def ownerInstance = Owner.get(id)
        if (!ownerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ownerInstance.version > version) {
                ownerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'owner.label', default: 'Owner')] as Object[],
                          "Another user has updated this Owner while you were editing")
                render(view: "edit", model: [ownerInstance: ownerInstance])
                return
            }
        }

        ownerInstance.properties = params

        if (!ownerInstance.save(flush: true)) {
            render(view: "edit", model: [ownerInstance: ownerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'owner.label', default: 'Owner'), ownerInstance.id])
        redirect(action: "show", id: ownerInstance.id)
    }

    // Deletes the specified owner instance
    // The argument is the primary key of the owner instance to delete
    def delete(Long id) {
        def ownerInstance = Owner.get(id)
        if (!ownerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
            return
        }

        try {
            ownerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "show", id: id)
        }
    }
}

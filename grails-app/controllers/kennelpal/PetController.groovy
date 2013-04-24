package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class PetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // default, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: pet/list.gsp
    // petInstanceList is a map of all the pets in the model
    // petInstanceTotal is the total number of pets in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [petInstanceList: Pet.list(params), petInstanceTotal: Pet.count()]
    }

    // Creates and saves to database new instance of pet
    def create() {
        [petInstance: new Pet(params)]
    }

    // Saves instance of pet to database
    // Returns to the pet/create.gsp view if save is not successful
    // If save is successful, redirects to pet/show.gsp view and shows newly created pet
    def save() {
        def petInstance = new Pet(params)
        if (!petInstance.save(flush: true)) {
            render(view: "create", model: [petInstance: petInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pet.label', default: 'Pet'), petInstance.id])
        redirect(action: "show", id: petInstance.id)
    }

    // Shows the pet instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the pet instance is returned
    // Else an error message is shown and they are redirected to the pet/list.gsp
    def show(Long id) {
        def petInstance = Pet.get(id)
        if (!petInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
            return
        }

        [petInstance: petInstance]
    }

    // Returns pet instance at primary key supplied as argument
    // If the primary key is invalid, the pet/list.gsp view is loaded
    // Else the pet instance at the specified primary key is returned
    def edit(Long id) {
        def petInstance = Pet.get(id)
        if (!petInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
            return
        }

        [petInstance: petInstance]
    }

    // Updates the pet instance at the primary key supplied as an argument
    // Compares the version of the existing pet instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the pet instance at the specified primary key is updated
    def update(Long id, Long version) {
        def petInstance = Pet.get(id)
        if (!petInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (petInstance.version > version) {
                petInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pet.label', default: 'Pet')] as Object[],
                          "Another user has updated this Pet while you were editing")
                render(view: "edit", model: [petInstance: petInstance])
                return
            }
        }

        petInstance.properties = params

        if (!petInstance.save(flush: true)) {
            render(view: "edit", model: [petInstance: petInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pet.label', default: 'Pet'), petInstance.id])
        redirect(action: "show", id: petInstance.id)
    }

    // Deletes the specified pet instance
    // The argument is the primary key of the pet instance to delete
    def delete(Long id) {
        def petInstance = Pet.get(id)
        if (!petInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
            return
        }

        try {
            petInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "show", id: id)
        }
    }
}

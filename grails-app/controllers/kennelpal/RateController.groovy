package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class RateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    //default, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: rate/list.gsp
    // contactInstanceList is a map of all the rates in the model
    // contactInstanceTotal is the total number of rates in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rateInstanceList: Rate.list(params), rateInstanceTotal: Rate.count()]
    }

    // Creates and saves to database new instance of rate
    def create() {
        [rateInstance: new Rate(params)]
    }

    // Saves instance of rate to database
    // Returns to the rate/create.gsp view if save is not successful
    // If save is successful, redirects to rate/show.gsp view and shows newly created rate
    def save() {
        def rateInstance = new Rate(params)
        if (!rateInstance.save(flush: true)) {
            render(view: "create", model: [rateInstance: rateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rate.label', default: 'Rate'), rateInstance.id])
        redirect(action: "show", id: rateInstance.id)
    }

    // Shows the rate instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the rate instance is returned
    // Else an error message is shown and they are redirected to the rate/list.gsp
    def show(Long id) {
        def rateInstance = Rate.get(id)
        if (!rateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
            return
        }

        [rateInstance: rateInstance]
    }

    // Returns rate instance at primary key supplied as argument
    // If the primary key is invalid, the rate/list.gsp view is loaded
    // Else the rate instance at the specified primary key is returned
    def edit(Long id) {
        def rateInstance = Rate.get(id)
        if (!rateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
            return
        }

        [rateInstance: rateInstance]
    }

    // Updates the rate instance at the primary key supplied as an argument
    // Compares the version of the existing rate instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the rate instance at the specified primary key is updated
    def update(Long id, Long version) {
        def rateInstance = Rate.get(id)
        if (!rateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rateInstance.version > version) {
                rateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rate.label', default: 'Rate')] as Object[],
                          "Another user has updated this Rate while you were editing")
                render(view: "edit", model: [rateInstance: rateInstance])
                return
            }
        }

        rateInstance.properties = params

        if (!rateInstance.save(flush: true)) {
            render(view: "edit", model: [rateInstance: rateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rate.label', default: 'Rate'), rateInstance.id])
        redirect(action: "show", id: rateInstance.id)
    }

    // Deletes the specified rate instance
    // The argument is the primary key of the rate instance to delete
    def delete(Long id) {
        def rateInstance = Rate.get(id)
        if (!rateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
            return
        }

        try {
            rateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "show", id: id)
        }
    }
}

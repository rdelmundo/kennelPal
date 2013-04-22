package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class RateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rateInstanceList: Rate.list(params), rateInstanceTotal: Rate.count()]
    }

    def create() {
        [rateInstance: new Rate(params)]
    }

    def save() {
        def rateInstance = new Rate(params)
        if (!rateInstance.save(flush: true)) {
            render(view: "create", model: [rateInstance: rateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rate.label', default: 'Rate'), rateInstance.id])
        redirect(action: "show", id: rateInstance.id)
    }

    def show(Long id) {
        def rateInstance = Rate.get(id)
        if (!rateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
            return
        }

        [rateInstance: rateInstance]
    }

    def edit(Long id) {
        def rateInstance = Rate.get(id)
        if (!rateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rate.label', default: 'Rate'), id])
            redirect(action: "list")
            return
        }

        [rateInstance: rateInstance]
    }

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

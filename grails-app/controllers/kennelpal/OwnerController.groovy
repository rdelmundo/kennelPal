package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class OwnerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ownerInstanceList: Owner.list(params), ownerInstanceTotal: Owner.count()]
    }

    def create() {
        [ownerInstance: new Owner(params)]
    }

    def save() {
        def ownerInstance = new Owner(params)
        if (!ownerInstance.save(flush: true)) {
            render(view: "create", model: [ownerInstance: ownerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'owner.label', default: 'Owner'), ownerInstance.id])
        redirect(action: "show", id: ownerInstance.id)
    }

    def show(Long id) {
        def ownerInstance = Owner.get(id)
        if (!ownerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
            return
        }

        [ownerInstance: ownerInstance]
    }

    def edit(Long id) {
        def ownerInstance = Owner.get(id)
        if (!ownerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'owner.label', default: 'Owner'), id])
            redirect(action: "list")
            return
        }

        [ownerInstance: ownerInstance]
    }

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

package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class PetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [petInstanceList: Pet.list(params), petInstanceTotal: Pet.count()]
    }

    def create() {
        [petInstance: new Pet(params)]
    }

    def save() {
        def petInstance = new Pet(params)
        if (!petInstance.save(flush: true)) {
            render(view: "create", model: [petInstance: petInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pet.label', default: 'Pet'), petInstance.id])
        redirect(action: "show", id: petInstance.id)
    }

    def show(Long id) {
        def petInstance = Pet.get(id)
        if (!petInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
            return
        }

        [petInstance: petInstance]
    }

    def edit(Long id) {
        def petInstance = Pet.get(id)
        if (!petInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pet.label', default: 'Pet'), id])
            redirect(action: "list")
            return
        }

        [petInstance: petInstance]
    }

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

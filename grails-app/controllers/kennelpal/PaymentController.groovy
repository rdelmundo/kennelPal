package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class PaymentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    //default, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: payment/list.gsp
    // paymentInstanceList is a map of all the payments in the model
    // paymentInstanceTotal is the total number of payments in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [paymentInstanceList: Payment.list(params), paymentInstanceTotal: Payment.count()]
    }

    // Creates and saves to database new instance of payment
    def create() {
        [paymentInstance: new Payment(params)]
    }

    // Saves instance of payment to database
    // Returns to the payment/create.gsp view if save is not successful
    // If save is successful, redirects to payment/show.gsp view and shows newly created payment
    def save() {
        def paymentInstance = new Payment(params)
        if (!paymentInstance.save(flush: true)) {
            render(view: "create", model: [paymentInstance: paymentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
        redirect(action: "show", id: paymentInstance.id)
    }

    // Shows the payment instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the payment instance is returned
    // Else an error message is shown and they are redirected to the payment/list.gsp
    def show(Long id) {
        def paymentInstance = Payment.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        [paymentInstance: paymentInstance]
    }

    // Returns payment instance at primary key supplied as argument
    // If the primary key is invalid, the payment/list.gsp view is loaded
    // Else the payment instance at the specified primary key is returned
    def edit(Long id) {
        def paymentInstance = Payment.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        [paymentInstance: paymentInstance]
    }

    // Updates the payment instance at the primary key supplied as an argument
    // Compares the version of the existing payment instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the payment instance at the specified primary key is updated
    def update(Long id, Long version) {
        def paymentInstance = Payment.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (paymentInstance.version > version) {
                paymentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'payment.label', default: 'Payment')] as Object[],
                          "Another user has updated this Payment while you were editing")
                render(view: "edit", model: [paymentInstance: paymentInstance])
                return
            }
        }

        paymentInstance.properties = params

        if (!paymentInstance.save(flush: true)) {
            render(view: "edit", model: [paymentInstance: paymentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
        redirect(action: "show", id: paymentInstance.id)
    }

    // Deletes the specified payment instance
    // The argument is the primary key of the payment instance to delete
    def delete(Long id) {
        def paymentInstance = Payment.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        try {
            paymentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "show", id: id)
        }
    }
}

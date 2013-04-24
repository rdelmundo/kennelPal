package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class ReservationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    //default, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: reservation/list.gsp
    // reservationInstanceList is a map of all the reservations in the model
    // reservationInstanceTotal is the total number of reservations in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
    }

    // Creates and saves to database new instance of reservation
    def create() {
        [reservationInstance: new Reservation(params)]
    }

    // Saves instance of reservation to database
    // Returns to the reservation/create.gsp view if save is not successful
    // If save is successful, redirects to reservation/show.gsp view and shows newly created reservation
    def save() {
        def reservationInstance = new Reservation(params)
        if (!reservationInstance.save(flush: true)) {
            render(view: "create", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    // Shows the reservation instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the reservation instance is returned
    // Else an error message is shown and they are redirected to the reservation/list.gsp
    def show(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    // Returns reservation instance at primary key supplied as argument
    // If the primary key is invalid, the reservation/list.gsp view is loaded
    // Else the reservation instance at the specified primary key is returned
    def edit(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    // Updates the reservation instance at the primary key supplied as an argument
    // Compares the version of the existing reservation instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the reservation instance at the specified primary key is updated
    def update(Long id, Long version) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationInstance.version > version) {
                reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservation.label', default: 'Reservation')] as Object[],
                          "Another user has updated this Reservation while you were editing")
                render(view: "edit", model: [reservationInstance: reservationInstance])
                return
            }
        }

        reservationInstance.properties = params

        if (!reservationInstance.save(flush: true)) {
            render(view: "edit", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    // Deletes the specified reservation instance
    // The argument is the primary key of the reservation instance to delete
    def delete(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        try {
            reservationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "show", id: id)
        }
    }
}

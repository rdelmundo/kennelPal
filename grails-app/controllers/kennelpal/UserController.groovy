package kennelpal

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // Renders the view user/login.gsp
    def login() {
        render(view:"login")
    }

    // Compares the username and password submitted at the login view
    // If the credentials are valid, the user/home.gsp view is loaded
    // Else the login view is reloaded
    def verify() {
        User u = User.findByUsername(params?.login)
        if (u?.password.equals(params?.password)) {    
            redirect(action:"home")
        }
        else {
            redirect(action:"login")
        }
    }

    // Loads user/home.gsp with map of all instances of reservation from the model
    def home() {
        [reservations:Reservation.list(params)]
    }

    // default, redirects to list action with provided parameters
    def index() {
        redirect(action: "list", params: params)
    }

    // list action, returns a pair of maps to the view: user/list.gsp
    // userInstanceList is a map of all the users in the model
    // userInstanceTotal is the total number of users in the model
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    // Creates and saves to database new instance of user
    def create() {
        [userInstance: new User(params)]
    }

    // Saves instance of user to database
    // Returns to the user/create.gsp view if save is not successful
    // If save is successful, redirects to user/show.gsp view and shows newly created user
    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    // Shows the user instance corresponding to the primary key supplied as argument
    // If the primary key is valid, the user instance is returned
    // Else an error message is shown and they are redirected to the user/list.gsp
    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    // Returns user instance at primary key supplied as argument
    // If the primary key is invalid, the user/list.gsp view is loaded
    // Else the user instance at the specified primary key is returned
    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    // Updates the user instance at the primary key supplied as an argument
    // Compares the version of the existing user instance in the persistent store with the
    //      instance that serves as the updated version
    // If the version passed is older than the version in the database, the update is rejected
    // Else the update is valid and the user instance at the specified primary key is updated
    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    // Deletes the specified user instance
    // The argument is the primary key of the user instance to delete
    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
}

package kennelpal



import org.junit.*
import grails.test.mixin.*

@TestFor(ReservationController)
@Mock(Reservation)
class ReservationControllerTests {

    // setup mock Reservation
    def populateValidParams(params) {
        assert params != null
        params["owner"] = new Owner(
            accountNumber:"99999",
            accountBalance:0.00, 
            firstName:"Flip", 
            lastName:"Sanders", 
            address:"765 Hickory Lane", 
            city:"Rochester",
            state:"55902", 
            zipcode:"59902",
            email:"chet@yahoo.com", 
            primaryPhone:"507-123-9876", 
            secondaryPhone:null, 
            notes:null
            )
        params["pet"] = new Pet(
            name:"Champ",
            owner:params.owner,
            breed:"Golden Retriever",
            notes:null,
            dateOfBirth:null
            )
        params["rate"] = new Rate(
            name:"Weekend",
            amount:25.00
            )
        params["startDate"] = new Date('2013/05/01')
        params["endDate"] = new Date('2013/05/08')
        params["checkedIn"] = false
    }

    // tests index action, asserts that accurate redirect occurred
    void testIndex() {
        controller.index()
        assert "/reservation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reservationInstanceList.size() == 0
        assert model.reservationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reservationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reservationInstance != null
        assert view == '/reservation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reservation/show/1'
        assert controller.flash.message != null
        assert Reservation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null

        params.id = reservation.id

        def model = controller.show()

        assert model.reservationInstance == reservation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null

        params.id = reservation.id

        def model = controller.edit()

        assert model.reservationInstance == reservation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        response.reset()

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null

        // test invalid parameters in update
        params.id = reservation.id
        //TODO: add invalid values to params object

        params.startDate = null

        controller.update()

        assert view == "/reservation/edit"
        assert model.reservationInstance != null

        reservation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reservation/show/$reservation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reservation.clearErrors()

        populateValidParams(params)
        params.id = reservation.id
        params.version = -1
        controller.update()

        assert view == "/reservation/edit"
        assert model.reservationInstance != null
        assert model.reservationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reservation/list'

        response.reset()

        populateValidParams(params)
        def reservation = new Reservation(params)

        assert reservation.save() != null
        assert Reservation.count() == 1

        params.id = reservation.id

        controller.delete()

        assert Reservation.count() == 0
        assert Reservation.get(reservation.id) == null
        assert response.redirectedUrl == '/reservation/list'
    }
}

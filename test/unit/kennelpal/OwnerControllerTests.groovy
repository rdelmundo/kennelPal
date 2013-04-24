package kennelpal



import org.junit.*
import grails.test.mixin.*

@TestFor(OwnerController)
@Mock(Owner)
class OwnerControllerTests {

    // setup mock Owner
    def populateValidParams(params) {
        assert params != null
        params["accountNumber"] = '99999'
        params["accountBalance"] = 0.00
        params["firstName"] = 'Flip'
        params["lastName"] = 'Sanders'
        params["address"] = '765 Hickory Lane' 
        params["city"] = 'Rochester'
        params["state"] = '55902' 
        params["zipcode"] = '59902'
        params["email"] = 'chet@yahoo.com' 
        params["primaryPhone"] = '507-123-9876' 
        params["secondaryPhone"] = null 
        params["notes"] = null
    }

    // tests index action, asserts that accurate redirect occurred
    void testIndex() {
        controller.index()
        assert "/owner/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ownerInstanceList.size() == 0
        assert model.ownerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.ownerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ownerInstance != null
        assert view == '/owner/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/owner/show/1'
        assert controller.flash.message != null
        assert Owner.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/owner/list'

        populateValidParams(params)
        def owner = new Owner(params)

        assert owner.save() != null

        params.id = owner.id

        def model = controller.show()

        assert model.ownerInstance == owner
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/owner/list'

        populateValidParams(params)
        def owner = new Owner(params)

        assert owner.save() != null

        params.id = owner.id

        def model = controller.edit()

        assert model.ownerInstance == owner
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/owner/list'

        response.reset()

        populateValidParams(params)
        def owner = new Owner(params)

        assert owner.save() != null

        // test invalid parameters in update
        params.id = owner.id
        //TODO: add invalid values to params object
        params.accountBalance = -1.00

        controller.update()

        assert view == "/owner/edit"
        assert model.ownerInstance != null

        owner.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/owner/show/$owner.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        owner.clearErrors()

        populateValidParams(params)
        params.id = owner.id
        params.version = -1
        controller.update()

        assert view == "/owner/edit"
        assert model.ownerInstance != null
        assert model.ownerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/owner/list'

        response.reset()

        populateValidParams(params)
        def owner = new Owner(params)

        assert owner.save() != null
        assert Owner.count() == 1

        params.id = owner.id

        controller.delete()

        assert Owner.count() == 0
        assert Owner.get(owner.id) == null
        assert response.redirectedUrl == '/owner/list'
    }
}

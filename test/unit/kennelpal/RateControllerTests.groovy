package kennelpal



import org.junit.*
import grails.test.mixin.*

@TestFor(RateController)
@Mock(Rate)
class RateControllerTests {

    // setup mock Rate
    def populateValidParams(params) {
        assert params != null
        params["name"] = "Weekend"
        params["amount"] = 25.00
     }

    // tests index action, asserts that accurate redirect occurred
    void testIndex() {
        controller.index()
        assert "/rate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rateInstanceList.size() == 0
        assert model.rateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rateInstance != null
        assert view == '/rate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rate/show/1'
        assert controller.flash.message != null
        assert Rate.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rate/list'

        populateValidParams(params)
        def rate = new Rate(params)

        assert rate.save() != null

        params.id = rate.id

        def model = controller.show()

        assert model.rateInstance == rate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rate/list'

        populateValidParams(params)
        def rate = new Rate(params)

        assert rate.save() != null

        params.id = rate.id

        def model = controller.edit()

        assert model.rateInstance == rate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rate/list'

        response.reset()

        populateValidParams(params)
        def rate = new Rate(params)

        assert rate.save() != null

        // test invalid parameters in update
        params.id = rate.id
        //TODO: add invalid values to params object

        params.amount = -1.00

        controller.update()

        assert view == "/rate/edit"
        assert model.rateInstance != null

        rate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rate/show/$rate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rate.clearErrors()

        populateValidParams(params)
        params.id = rate.id
        params.version = -1
        controller.update()

        assert view == "/rate/edit"
        assert model.rateInstance != null
        assert model.rateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rate/list'

        response.reset()

        populateValidParams(params)
        def rate = new Rate(params)

        assert rate.save() != null
        assert Rate.count() == 1

        params.id = rate.id

        controller.delete()

        assert Rate.count() == 0
        assert Rate.get(rate.id) == null
        assert response.redirectedUrl == '/rate/list'
    }
}

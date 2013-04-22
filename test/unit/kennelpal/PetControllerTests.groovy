package kennelpal



import org.junit.*
import grails.test.mixin.*

@TestFor(PetController)
@Mock(Pet)
class PetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.petInstanceList.size() == 0
        assert model.petInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.petInstance != null
    }

    void testSave() {
        controller.save()

        assert model.petInstance != null
        assert view == '/pet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pet/show/1'
        assert controller.flash.message != null
        assert Pet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pet/list'

        populateValidParams(params)
        def pet = new Pet(params)

        assert pet.save() != null

        params.id = pet.id

        def model = controller.show()

        assert model.petInstance == pet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pet/list'

        populateValidParams(params)
        def pet = new Pet(params)

        assert pet.save() != null

        params.id = pet.id

        def model = controller.edit()

        assert model.petInstance == pet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pet/list'

        response.reset()

        populateValidParams(params)
        def pet = new Pet(params)

        assert pet.save() != null

        // test invalid parameters in update
        params.id = pet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pet/edit"
        assert model.petInstance != null

        pet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pet/show/$pet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pet.clearErrors()

        populateValidParams(params)
        params.id = pet.id
        params.version = -1
        controller.update()

        assert view == "/pet/edit"
        assert model.petInstance != null
        assert model.petInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pet/list'

        response.reset()

        populateValidParams(params)
        def pet = new Pet(params)

        assert pet.save() != null
        assert Pet.count() == 1

        params.id = pet.id

        controller.delete()

        assert Pet.count() == 0
        assert Pet.get(pet.id) == null
        assert response.redirectedUrl == '/pet/list'
    }
}

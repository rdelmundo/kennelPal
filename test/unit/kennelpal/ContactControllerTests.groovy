package kennelpal



import org.junit.*
import grails.test.mixin.*

@TestFor(ContactController)
@Mock(Contact)
class ContactControllerTests {

    // setup mock Contact
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
        params["firstName"] = 'Chuck'
        params["lastName"] = 'Bednarik'
        params["phone"] = '612-098-8798'
        params["email"] = null
        params["role"] = 'Veternarian'
        params["notes"] = null
    }

    // tests index action
    void testIndex() {
        controller.index()
        assert "/contact/list" == response.redirectedUrl
    }

    // tests list action, 
    void testList() {

        def model = controller.list()

        assert model.contactInstanceList.size() == 0
        assert model.contactInstanceTotal == 0
    }

    // tests create action
    void testCreate() {
        def model = controller.create()

        assert model.contactInstance != null
    }

    // tests save action
    void testSave() {
        controller.save()

        assert model.contactInstance != null
        assert view == '/contact/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contact/show/1'
        assert controller.flash.message != null
        assert Contact.count() == 1
    }

    // tests show action
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contact/list'

        populateValidParams(params)
        def contact = new Contact(params)

        assert contact.save() != null

        params.id = contact.id

        def model = controller.show()

        assert model.contactInstance == contact
    }

    // tests edit action
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contact/list'

        populateValidParams(params)
        def contact = new Contact(params)

        assert contact.save() != null

        params.id = contact.id

        def model = controller.edit()

        assert model.contactInstance == contact
    }

    // tests update action
    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contact/list'

        response.reset()

        populateValidParams(params)
        def contact = new Contact(params)

        assert contact.save() != null

        // test invalid parameters in update
        params.id = contact.id
        //TODO: add invalid values to params object
        params.role = "Some role not inList!"

        controller.update()

        assert view == "/contact/edit"
        assert model.contactInstance != null

        contact.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contact/show/$contact.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contact.clearErrors()

        populateValidParams(params)
        params.id = contact.id
        params.version = -1
        controller.update()

        assert view == "/contact/edit"
        assert model.contactInstance != null
        assert model.contactInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    // tests delete action
    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contact/list'

        response.reset()

        populateValidParams(params)
        def contact = new Contact(params)

        assert contact.save() != null
        assert Contact.count() == 1

        params.id = contact.id

        controller.delete()

        assert Contact.count() == 0
        assert Contact.get(contact.id) == null
        assert response.redirectedUrl == '/contact/list'
    }
}

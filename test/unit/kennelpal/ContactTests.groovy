package kennelpal



import grails.test.mixin.*
import org.junit.*
import kennelpal.Owner

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Contact)
class ContactTests {

	def mockContact

	@Before
	def void setUp() {
		mockContact = initMockContact()
	}

	@After
    void tearDown() {
    	mockContact = null
    }

	def initMockContact() {
		Contact c = new Contact(
			owner:initMockOwner(),
			firstName:"Joe",
			lastName:"John",
			phone:"612-098-0908",
			email:"joejohn@gmail.com",
			role:"Family",
			notes:null
			)
		c
	}

	def initMockOwner() {
		Owner mockOwner = new Owner(
            accountNumber:"99999",
            accountBalance:0.00, 
            firstName:"Mary", 
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
		mockOwner
	}

    void testToString() {
    	assertEquals("${mockContact.lastName}, ${mockContact.firstName} ${mockContact.role}", mockContact.toString())
    }
}

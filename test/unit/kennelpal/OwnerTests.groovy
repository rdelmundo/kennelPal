package kennelpal



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Owner)
class OwnerTests {

    // declare mock Owner
	def mockOwner

    // initialize mock Owner before each test
	@Before
	void setUp() {
		mockOwner = initMockOwner()
	}

    // set mock Owner to null after each test
	@After
	void tearDown() {
		mockOwner = null
	}

    // method to setup mock Owner
	Owner initMockOwner() {
		Owner tempMockOwner = new Owner(
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
		tempMockOwner
	}

    // test to see if expected string is returned
    void testToString() {
    	assertEquals("${mockOwner.lastName}, ${mockOwner.firstName}  tel: ${mockOwner.primaryPhone}", mockOwner.toString())
    }
}

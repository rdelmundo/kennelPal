package kennelpal



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Owner)
class OwnerTests {

	def mockOwner

	@Before
	void setUp() {
		mockOwner = initMockOwner()
	}

	@After
	void tearDown() {
		mockOwner = null
	}

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

    void testToString() {
    	assertEquals("${mockOwner.lastName}, ${mockOwner.firstName}  tel: ${mockOwner.primaryPhone}", mockOwner.toString())
    }
}

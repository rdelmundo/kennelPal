package kennelpal



import grails.test.mixin.*
import org.junit.*
import kennelpal.Owner

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Payment)
class PaymentTests {

	// declare mock Payment
	def mockPayment

	// initialize mock Payment before each test
	@Before
	void setUp() {
		mockPayment = initMockPayment()
	}

	// set mock Payment to null after each test
	@After
	void tearDown() {
		mockPayment = null
	}

	// method to setup mock Payment
	Payment initMockPayment() {
		Payment tempMockPayment = new Payment(
			owner:initMockOwner(),
			amount:100.00,
			dateReceived:new Date()
			)
		tempMockPayment
	}

    // method to setup mock Owner
	Owner initMockOwner() {
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

	// test to see if expected string is returned
    void testToString() {
    	assertEquals("${mockPayment.owner.accountNumber} ${mockPayment.amount} ${mockPayment.dateReceived}", mockPayment.toString())
    }
}

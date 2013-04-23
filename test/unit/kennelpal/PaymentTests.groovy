package kennelpal



import grails.test.mixin.*
import org.junit.*
import kennelpal.Owner

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Payment)
class PaymentTests {

	def mockPayment

	@Before
	void setUp() {
		mockPayment = initMockPayment()
	}

	@After
	void tearDown() {
		mockPayment = null
	}

	Payment initMockPayment() {
		Payment tempMockPayment = new Payment(
			owner:initMockOwner(),
			amount:100.00,
			dateReceived:new Date()
			)
		tempMockPayment
	}

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

    void testToString() {
    	assertEquals("${mockPayment.owner.accountNumber} ${mockPayment.amount} ${mockPayment.dateReceived}", mockPayment.toString())
    }
}

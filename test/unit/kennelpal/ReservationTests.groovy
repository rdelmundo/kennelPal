package kennelpal



import grails.test.mixin.*
import org.junit.*
import kennelpal.Owner
import kennelpal.Pet
import kennelpal.Rate

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {

	// declare mock Reservation
	def mockReservation

	// initialize mock Reservation before each test
	@Before
	void setUp() {
		mockReservation = initMockReservation()
	}

	// set mock Reservation to null after each test
	@After
	void tearDown() {
		mockReservation = null
	}

    // method to setup mock Reservation
	Reservation initMockReservation() {
		Reservation tempMockReservation = new Reservation(
			owner:initMockOwner(),
			pet:initMockPet(),
			rate:initMockRate(),
			startDate:new Date(),
			endDate:new Date() + 1,
			checkedIn:false
			)
		tempMockReservation
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

    // method to setup mock Pet
	Pet initMockPet() {
		Pet mockPet = new Pet(
			name:"Fido",
			owner:initMockOwner(),
			breed:"Bulldog",
			notes:null,
			dateOfBirth:new Date()
			)
		mockPet
	}

    // method to setup mock Rate
	Rate initMockRate() {
		Rate mockRate = new Rate(
			name:"Weekend",
			amount:25.00
			)
		mockRate
	}

	// test to see if expected string is returned
    void testToString() {
    	assertEquals("${mockReservation.startDate} ${mockReservation.pet.name} ${mockReservation.owner.lastName}, ${mockReservation.owner.firstName}", mockReservation.toString())
    }
}

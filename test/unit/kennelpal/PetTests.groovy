package kennelpal



import grails.test.mixin.*
import org.junit.*
import kennelpal.Owner

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Pet)
class PetTests {

	def mockPet

	@Before
	void setUp() {
		mockPet = initMockPet()
	}

	@After
	void tearDown() {
		mockPet = null
	}

	Pet initMockPet() {
		Pet tempMockPet = new Pet(
			name:"Fido",
			owner:initMockOwner(),
			breed:"Bulldog",
			notes:null,
			dateOfBirth:new Date()
			)
		tempMockPet
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
    	assertEquals("${mockPet.name}", mockPet.toString())
    }
}

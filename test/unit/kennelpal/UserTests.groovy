package kennelpal



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

	// declare mock User
	def mockUser

	// initialize mock User before each test
	@Before
	void setUp() {
		mockUser = initMockUser()
	}

	// set mock User to null after each test
	@After 
	void tearDown() {
		mockUser = null
	}

    // method to setup mock User
	User initMockUser() {
		User tempMockUser = new User(
			username:"username",
			password:"password",
			firstName:"Joe",
			lastName:"John",
			role:"user"
			)
		tempMockUser
	}

	// test to see if expected string is returned
    void testToString() {
    	assertEquals("${mockUser.username}", mockUser.toString())
    }
}

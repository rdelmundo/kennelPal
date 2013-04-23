package kennelpal



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

	def mockUser

	@Before
	void setUp() {
		mockUser = initMockUser()
	}

	@After 
	void tearDown() {
		mockUser = null
	}

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

    void testToString() {
    	assertEquals("${mockUser.username}", mockUser.toString())
    }
}

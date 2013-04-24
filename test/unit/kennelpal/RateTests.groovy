package kennelpal



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Rate)
class RateTests {

	// declare mock Rate
	def mockRate

	// initialize mock Rate before each test
	@Before
	void setUp() {
		mockRate = initMockRate()
	}

	// set mock Rate to null after each test
	@After
	void tearDown() {
		mockRate = null
	}

    // method to setup mock Rate
	Rate initMockRate() {
		Rate tempMockRate = new Rate(
			name:"Weekday",
			amount:25.00
			)
		tempMockRate
	}

	// test to see if expected string is returned
    void testToString() {
    	assertEquals("${mockRate.name}, \$${mockRate.amount}", mockRate.toString())
    }
}

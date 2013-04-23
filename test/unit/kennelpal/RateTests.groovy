package kennelpal



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Rate)
class RateTests {

	def mockRate

	@Before
	void setUp() {
		mockRate = initMockRate()
	}

	@After
	void tearDown() {
		mockRate = null
	}

	Rate initMockRate() {
		Rate tempMockRate = new Rate(
			name:"Weekday",
			amount:25.00
			)
		tempMockRate
	}

    void testToString() {
    	assertEquals("${mockRate.name}, \$${mockRate.amount}", mockRate.toString())
    }
}

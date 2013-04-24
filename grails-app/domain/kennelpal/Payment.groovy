package kennelpal

class Payment {

    // instance variables - columns
	Owner owner    		      // owner making the payment
	BigDecimal amount         // amount of the payment
	Date dateReceived         // date the payment was received/processed

    // setup validation and constraints
    static constraints = {
    	owner()
    	amount(min:0.00, scale:2)
    	dateReceived()
    }

    // Define ordering for Payment model
    static mapping = {
    	sort "dateReceived"
    }

    // Define string returned in reference to an instance of Payment
    String toString() {
    	"${owner.accountNumber} ${amount} ${dateReceived}"
    }
}

package kennelpal

class Rate {

    // instance variables - columns
	String name                // name of the rate, example: "Weekday"
	BigDecimal amount          // price associated with the Rate

    // define validation and constraints
    static constraints = {
    	name(blank:false, nullable:false, maxSize:25)
    	amount(min:0.00, scale:2)
    }

    // Rate has a one to many relationship with the following classes...
    static hasMany = [reservations:Reservation] 

    // Define ordering for Rate model
    static mapping = {
    	sort "amount"
    }

    // Define string returned in reference to an instance of Rate
    String toString() {
    	"${name}, \$${amount}"
    }
}

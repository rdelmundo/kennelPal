package kennelpal

class Rate {

	String name 
	BigDecimal amount

    static constraints = {
    	name(blank:false, nullable:false, maxSize:25)
    	amount(min:0.00, scale:2)
    }

    static hasMany = [reservations:Reservation] 

    static mapping = {
    	sort "amount"
    }

    String toString() {
    	"${name}, \$${amount}"
    }
}

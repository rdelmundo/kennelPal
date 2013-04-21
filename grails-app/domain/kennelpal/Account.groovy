package kennelpal

class Account {

	String accountNumber		// use id as accountNumber instead?
	BigDecimal balance = 0.00
	Date dateCreated
	Date lastUpdated

    static constraints = {
    	accountNumber(size:5..5, unique:true)
    	balance(min:0.00, scale:2)
    	dateCreated()
    	lastUpdated()
    }

    //static hasOne = [owner:Owner]
    //static hasMany = [pets:Pet, reservations:Reservation, payments:Payment]

    String toString() {
    	"${accountNumber} ${owner.lastName}, ${owner.firstName}    \$${balance}"
    }

}

package kennelpal

class Pet {

	Account account     			// 1 (Account) : Many (Pets)
	String name
	String breed
	Date dateOfBirth

    static constraints = {
    	account()
    	name(blank:false, nullable:false, size:1..25)
    	breed(maxSize:50)
    	dateOfBirth() 				// add validation, make sure in past
    }

    static hasMany = [reservations:Reservation]   

    static mapping = {
    	sort "name"
    }

    String toString() {
    	"${name}"
    }
}

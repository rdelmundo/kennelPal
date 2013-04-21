package kennelpal

class Pet {

	Owner owner     			// 1 (Owner) : Many (Pets)
	String name
	String breed
	Date dateOfBirth

    static constraints = {
    	owner()
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

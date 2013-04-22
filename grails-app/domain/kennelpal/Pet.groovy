package kennelpal

class Pet {

	String name
    Owner owner                 // 1 (Owner) : Many (Pets)
	String breed
    String notes
	Date dateOfBirth

    static constraints = {
        name(blank:false, nullable:false, size:1..25)
    	owner(blank:false, nullable:false)
    	breed(maxSize:50)
        notes(blank:true, nullable:true, maxSize:500)
    	dateOfBirth(blank:true, nullable:true)				// add validation, make sure in past
    }

    static hasMany = [reservations:Reservation]   

    static mapping = {
    	sort "name"
    }

    String toString() {
    	"${name}"
    }
}

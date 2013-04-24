package kennelpal

class Reservation {

    // instance variables - columns
	Owner owner                // owner of pet that has reservation
	Pet pet                    // pet that has reservation
	Rate rate                  // rate for reservation
	Date startDate             // start date of reservation
	Date endDate               // end date of reservation
	// Integer days		       // or use this instead of endDate?	
	Boolean checkedIn = false  // is the pet checked in?

    // define validation and constraints
    static constraints = {
    	owner(blank:false, nullable:false)
    	pet(blank:false, nullable:false)
    	rate(blank:false, nullable:false) 
    	startDate()                             // validate = new Date() or in future
    	endDate()			                    // validate on or after startDate
    	checkedIn()
    }

    // Define ordering for Reservation model
    static mapping = {
    	sort "startDate"
    }

    /*
    Integer lengthOfReservationInDays() {
    }
    */
    
    // Define string returned in reference to an instance of Reservation
    String toString() {
    	"${startDate} ${pet.name} ${owner.lastName}, ${owner.firstName}"
    }
}

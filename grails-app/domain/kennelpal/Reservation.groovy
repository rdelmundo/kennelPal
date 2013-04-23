package kennelpal

class Reservation {

	Owner owner
	Pet pet
	Rate rate
	Date startDate
	Date endDate
	// Integer days		or use this instead of endDate?	
	Boolean checkedIn = false

    static constraints = {
    	owner()
    	pet()
    	rate() 
    	startDate()         // validate in future
    	endDate()			// validate on or after startDate
    	checkedIn()
    }

    static mapping = {
    	sort "startDate"
    }

    /*
    Integer lengthOfReservationInDays() {
    }
    */
    
    String toString() {
    	"${startDate} ${pet.name} ${owner.lastName}, $owner.firstName}"
    }
}

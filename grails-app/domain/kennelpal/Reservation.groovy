package kennelpal

class Reservation {

	Account account
	Pet pet
	Rate rate
	Date startDate
	Date endDate
	// Integer days		or use this instead of endDate?	
	Boolean checkedIn = false

    static constraints = {
    	account()
    	pet()
    	rate() 
    	startDate()         // validate in future
    	endDate()			// validate after startDate
    	checkedIn()
    }

    static mapping = {
    	sort "startDate"
    }

    String toString() {
    	"${startDate} ${pet.name} ${account.owner.lastName}, $account.owner.firstName}"
    }
}

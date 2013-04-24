package kennelpal

class Vaccination {

    // instance variables - columns
	Pet pet                    // pet belonging to vaccination instance
	VaccinationTypes type      // type of vaccination
	Date dateReceived          // date vaccination was last received
	Date dateDue               // date the vaccination is next due

    // define validation and constraints
    static constraints = {
    	pet()                  
    	type()
    	dateReceived()
    	dateDue()
    }

    // A vaccination has a single type
    static belongsTo = [type:VaccinationTypes]

    // Define ordering for Vaccination model
    static mapping = {
    	sort "pet.name"
    }

    // Define string returned in reference to an instance of Vaccination
    String toString() {
    	"${pet} ${type}"
    }
}

package kennelpal

class Vaccination {

	Pet pet 
	VaccinationTypes type
	Date dateReceived
	Date dateDue


    static constraints = {
    	pet()
    	type()
    	dateReceived()
    	dateDue()
    }

    static belongsTo = [type:VaccinationTypes]

    static mapping = {
    	sort "pet.name"
    }

    String toString() {
    	"${pet} ${type}"
    }
}

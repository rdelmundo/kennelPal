package kennelpal

class VaccinationTypes {

	String name 
	String description
	String renewalPeriod

    static constraints = {
    	name(blank:false, nullable:false, maxSize:50, unique:true)
    	description(maxSize:100);
    	renewalPeriod()
    }

    static hasMany = [vaccinations:Vaccination]

    static mapping = {
    	sort "name"
    }

    String toString() {
    	"${name} ${description}"
    }
}

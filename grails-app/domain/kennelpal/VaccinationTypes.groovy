package kennelpal

class VaccinationTypes {

    // instance variables - columns
	String name                // medical name of vaccination
	String description         // medical description of vaccination
    Integer numberOf           // number of 
	String renewalPeriod       // renewal timeframe

    static constraints = {
    	name(blank:false, nullable:false, maxSize:50, unique:true)
    	description(maxSize:100);
        numberOf(blank:false, nullable:false, min:1, max:52)
    	renewalPeriod(blank:false, nullable:false, inList:["Day(s)", "Week(s)", "Month(s)", "Year(s)"])
    }

    // an instance of VaccinationTypes has a one to many relationship with Vaccination...
    static hasMany = [vaccinations:Vaccination]

    // Define ordering for VaccinationTypes model
    static mapping = {
    	sort "name"
    }

    // Define string returned in reference to an instance of VaccinationTypes
    String toString() {
    	"${name} ${description}"
    }
}

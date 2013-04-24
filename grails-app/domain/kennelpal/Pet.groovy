package kennelpal

class Pet {

    // instance variables - columns
	String name                // name of pet
    Owner owner                // owner of pet 
	String breed               // breed of pet
    String notes               // miscellaneous notes regarding the pet
	Date dateOfBirth           // birthday of the pet, adorable

    // setup validation and constraints
    static constraints = {
        name(blank:false, nullable:false, size:1..25)
    	owner(blank:false, nullable:false)
    	breed(maxSize:50)
        notes(blank:true, nullable:true, maxSize:500)
    	dateOfBirth(blank:true, nullable:true)				// add validation, make sure in past
    }

    // Pet class has a one to many relationship with the following classes...
    static hasMany = [reservations:Reservation]   

    // Define ordering for Pet model
    static mapping = {
    	sort "name"
    }

    // Define string returned in reference to an instance of Pet
    String toString() {
    	"${name}"
    }
}

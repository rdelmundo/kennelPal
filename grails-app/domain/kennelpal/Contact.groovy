package kennelpal

class Contact {

    // Instance variables - columns
	Owner owner 	      // the owner the contact corresponds to
	String firstName      // first name of contact
	String lastName       // Last name of contact
	String phone          // phone number of contact
	String email          // contact email
	String role           // relationship of the contact to the owner
	String notes          // Miscellaneous notes regarding the contact

    // Validation and constraints for instance variables
    static constraints = {
    	lastName(blank:false, nullable:false, size:1..25)
        firstName(blank:false, nullable:false, size:1..25)
        owner()
    	phone(blank:false, nullable:false)   // add regex validator
    	email(blank:true, nullable:true, email:true)
    	role(inList:["Family", "Friend", "Veternarian", "Other"])
    	notes(blank:true, nullable:true, maxSize:100)
    }

    // Define ordering for Contact model
    static mapping = {
    	sort "lastName"
    }

    // Define string returned in reference to an instance of Contact
    String toString() {
    	"${lastName}, ${firstName} ${role}"
    }
}

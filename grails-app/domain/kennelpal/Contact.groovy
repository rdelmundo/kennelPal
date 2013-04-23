package kennelpal

class Contact {

	Owner owner 		// One (Owner) : Many (Contact)
	String firstName
	String lastName
	String phone
	String email
	String role
	String notes

    static constraints = {
    	owner()
    	firstName(blank:false, nullable:false, size:1..25)
    	lastName(blank:false, nullable:false, size:1..25)
    	phone(blank:false, nullable:false)   // add regex validator
    	email(blank:true, nullable:true, email:true)
    	role(inList:["Family", "Friend", "Veternarian", "Other"])
    	notes(blank:true, nullable:true, maxSize:100)
    }

    static mapping = {
    	sort "lastName"
    }

    String toString() {
    	"${lastName}, ${firstName} ${role}"
    }
}

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
    	email(email:true)
    	role(inList:["Family", "Friend", "Veternarian", "Other"])
    	notes(maxSize:100)
    }

    static mapping = {
    	sort "owner.lastName"
    }

    String toString() {
    	"${lastName}, ${firstName} ${role}"
    }
}

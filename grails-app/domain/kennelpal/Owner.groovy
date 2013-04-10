package kennelpal

class Owner {

	Account account    		// 1 : 1 with Account
	String firstName
	String lastName
	String address
	String city
	String state = "MN"
	String zipcode
	String email
	String primaryPhone
	String secondaryPhone
	String notes

    static constraints = {
    	account()
    	firstName(blank:false, nullable:false, size:1..25)
    	lastName(blank:false, nullable:false, size:1..25)
    	address()
    	city() 
    	state() 
    	zipcode() 
    	email(email:true)
    	primaryPhone(blank:false, nullable:false) 	// add regex validator
    	secondaryPhone() 						   	// add regex
    	notes(maxSize:500)
    }

    static hasMany = [contacts:Contact]

    static mapping = {
    	sort "lastName"
    }

    String toString() {
    	"${lastName}, ${firstName}  tel: ${primaryPhone}"
    }
}

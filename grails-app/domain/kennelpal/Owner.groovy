package kennelpal

// the Owner class represents the basic account unit
class Owner {

    String accountNumber
    BigDecimal accountBalance
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
    Date dateCreated
    Date lastUpdated

    static constraints = {
    	accountNumber(blank:false, nullable:false, size:5..5, unique:true)
        accountBalance(min:0.00, scale:2)
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
        dateCreated()
        lastUpdated()
    }

    static hasMany = [pets:Pet, contacts:Contact, reservations:Reservation, payments:Payment]

    static mapping = {
    	sort "lastName"
    }

    String toString() {
    	"${lastName}, ${firstName}  tel: ${primaryPhone}"
    }
}

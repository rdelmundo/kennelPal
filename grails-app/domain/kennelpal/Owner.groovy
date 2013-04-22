package kennelpal

// the Owner class represents the basic account unit
class Owner {

    String accountNumber
    BigDecimal accountBalance = 0.00
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
    	address(blank:true, nullable:true)
    	city(blank:true, nullable:true) 
    	state(blank:true, nullable:true)
    	zipcode(blank:true, nullable:true)
    	email(email:true)
    	primaryPhone(blank:false, nullable:false) 	// add regex validator
    	secondaryPhone(blank:true, nullable:true)   // add regex
    	notes(blank:true, nullable:true, maxSize:500)
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

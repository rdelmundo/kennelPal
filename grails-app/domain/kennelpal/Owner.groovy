package kennelpal

// the Owner class represents the basic account unit
class Owner {

    // instance variables - columns
    String accountNumber                // account number, specified by employee
    BigDecimal accountBalance = 0.00    // account balance for owner, default value of 0.00
	String firstName                    // first name of owner
	String lastName                     // last name of owner
	String address                      // address of owner
	String city                         // ...city
	String state = "MN"                 // ...state, default is MN
	String zipcode                      // ...zip code
	String email                        // owner email
	String primaryPhone                 // primary phone number
	String secondaryPhone               // alternate phone number        
	String notes                        // miscellaneous notes regarding the owner
    Date dateCreated                    // auto-generated creation date of owner instance
    Date lastUpdated                    // auto-generated date of last update of owner instance

    // Setup validation and constraints
    static constraints = {
    	accountNumber(blank:false, nullable:false, size:5..5, unique:true)
        accountBalance(min:0.00, scale:2)
    	firstName(blank:false, nullable:false, size:1..25)
    	lastName(blank:false, nullable:false, size:1..25)
    	address(blank:true, nullable:true)
    	city(blank:true, nullable:true) 
    	state(blank:true, nullable:true)
    	zipcode(blank:true, nullable:true, matches: '\\d{5}' )
    	email(email:true)
    	primaryPhone(blank:false, nullable:false, matches: '\\d{3}\\-\\d{3}\\-\\d{4}') 	// add regex validator
    	secondaryPhone(blank:true, nullable:true, matches: '\\d{3}\\-\\d{3}\\-\\d{4}')   // add regex
    	notes(blank:true, nullable:true, maxSize:500)
        dateCreated()
        lastUpdated()
    }

    // Owner model has a one to many relationship with the following classes...
    static hasMany = [pets:Pet, contacts:Contact, reservations:Reservation, payments:Payment]

    // Define ordering for Owner model
    static mapping = {
    	sort "lastName"
    }

    // Define string returned in reference to an instance of Owner
    String toString() {
    	"${lastName}, ${firstName}  tel: ${primaryPhone}"
    }
}

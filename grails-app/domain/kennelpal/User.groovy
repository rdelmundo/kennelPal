package kennelpal

class User {

    // instance variables - columns
	String username            // username for user or admin
	String password            // password for user or admin   
    String firstName           // first name of user or admin
    String lastName            // last name of user or admin
	String role = "user"       // role defines system privileges 

    // define validation and constraints
    static constraints = {
    	username blank:false, nullable:false, unique:true, size:6..12 
    	password blank:false, nullable:false, password:true, size:6..12
        firstName blank:false, nullable:false, size:1..25
        lastName blank:false, nullable:false, size:1..25
        role inList:["user","admin"]
    }

    // Define ordering for User model
    static mapping = {
    	sort "username"
    }

    // Define string returned in reference to an instance of User
    String toString() {
    	"${username}"
    }
}

package kennelpal

class User {

	String username
	String password
    String firstName
    String lastName
	String role = "user"

    static constraints = {
    	username unique:true, size:6..12
    	password password:true, size:6..12
        firstName blank:false, nullable:false, size:1..25
        lastName blank:false, nullable:false, size:1..25
        role inList:["user","admin"]
    }

    static mapping = {
    	sort "username"
    }

    String toString() {
    	"${username}"
    }
}

package kennelpal

class User {

	String username
	String password
	String role = "user"

    static constraints = {
    	username unique:true, size:6..12
    	password password:true, size:6..12
    	role inList:["user","admin"]
    }

    static mapping = {
    	sort "username"
    }

    String toString() {
    	"${username}"
    }
}

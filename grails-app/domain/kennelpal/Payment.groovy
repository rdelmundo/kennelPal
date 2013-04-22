package kennelpal

class Payment {

	Owner owner    		// 1 (Account) : Many (Payments)
	BigDecimal amount
	Date dateReceived

    static constraints = {
    	owner()
    	amount(min:0.00, scale:2)
    	dateReceived()
    }

    static mapping = {
    	sort "dateReceived"
    }

    String toString() {
    	"${account.accountNumber} ${amount} ${dateReceived}"
    }
}

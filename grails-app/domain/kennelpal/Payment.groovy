package kennelpal

class Payment {

	Account account     		// 1 (Account) : Many (Payments)
	BigDecimal amount
	Date dateReceived

    static constraints = {
    	account()
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

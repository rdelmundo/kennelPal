import grails.util.GrailsUtil
import kennelpal.Owner
import kennelpal.User


class BootStrap {

    def init = { servletContext ->

    	//Create Users
    	def admin = new User(
    		username:"admin0", 
    		password:"password", 
    		firstName:"Walt", 
    		lastName:"Phelps", 
    		role:"admin"
    		)
    	admin.save()

    	if (admin.hasErrors()) {
    		println admin.errors
    	}

    	def user = new User(
    		username:"user00", 
    		password:"password", 
    		firstName:"Penelope", 
    		lastName:"Rodgers", 
    		role:"user"
    		)
    	user.save()

    	if (user.hasErrors()) {
    		println user.errors
    	}

    	//Create Owners
    	def bill = new Owner(
    		accountNumber:"00001", 
    		accountBalance:0.00,
    		firstName:"Bill", 
    		lastName:"Jones", 
    		address:"123 Oak Street", 
    		city:"Rochester", 
    		state:"MN", 
    		zipcode:"55902", 
    		email:"bill@gmail.com", 
    		primaryPhone:"507-456-7890", 
    		secondaryPhone:null, 
    		notes:null
    		)
    	bill.save()

    	if (bill.hasErrors()) {
    		println bill.errors
    	}

    	def jane = new Owner(
    		accountNumber:"00002",
    		accountBalance:0.00, 
    		firstName:"Jane", 
    		lastName:"Smith", 
    		address:"345 Walnut Avenue", 
    		city:"Rochester",
    		state:"MN", 
    		zipcode:"55902", 
    		email:"jane@gmail.com", 
    		primaryPhone:"507-123-4321", 
    		secondaryPhone:null, 
    		notes:null
    		)
    	jane.save()

    	if (jane.hasErrors()) {
    		println jane.errors
    	}

    	def chet = new Owner(
    		accountNumber:"00003",
    		accountBalance:0.00, 
    		firstName:"Chet", 
    		lastName:"Thomas", 
    		address:"765 Hickory Lane", 
    		city:"Rochester",
    		state:"55902", 
    		zipcode:"59902",
    		email:"chet@yahoo.com", 
    		primaryPhone:"507-123-9876", 
    		secondaryPhone:null, 
    		notes:null
    		)
    	chet.save()

    	if (chet.hasErrors()) {
    		println chet.errors
    	}

    }
    def destroy = {
    }



}

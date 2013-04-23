import grails.util.GrailsUtil
import kennelpal.User
import kennelpal.Owner
import kennelpal.Pet
import kennelpal.Rate
import kennelpal.Reservation
import kennelpal.Payment
import kennelpal.Contact 


class BootStrap {

    def init = { servletContext ->

    	// ****************************************** Create Users
    	def admin = new User(
    		username:"kpadmin", 
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
    		username:"kpuser", 
    		password:"password", 
    		firstName:"Penelope", 
    		lastName:"Rodgers", 
    		role:"user"
    		)
    	user.save()

    	if (user.hasErrors()) {
    		println user.errors
    	}

    	// ****************************************** Create Owners
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

    	// ****************************************** Create Pets
    	def fido = new Pet(
    		owner:bill,
    		name:"Fido",
    		breed:"Golden Retriever",
    		notes:null,
    		dateOfBirth:null
    		)
    	fido.save()

    	if (fido.hasErrors()) {
    		println fido.errors
    	}

    	def lucky = new Pet(
    		owner:bill,
    		name:"Lucky",
    		breed:"New Foundland",
    		notes:null,
    		dateOfBirth:null
    		)
    	lucky.save()

    	if (lucky.hasErrors()) {
    		println lucky.errors
    	}

    	def buddy = new Pet(
    		owner:bill,
    		name:"Buddy",
    		breed:"New Foundland",
    		notes:null,
    		dateOfBirth:null
    		)
    	buddy.save()

    	if (buddy.hasErrors()) {
    		println lucky.errors
    	}

    	def fritz = new Pet(
    		owner:jane,
    		name:"Fritz",
    		breed:"Bernese Mountain Dog",
    		notes:null,
    		dateOfBirth:null
    		)
    	fritz.save()

    	if (fritz.hasErrors()) {
    		println lucky.errors
    	}

    	def franz = new Pet(
    		owner:jane,
    		name:"Franz",
    		breed:"German Shepherd",
    		notes:null,
    		dateOfBirth:null
    		)
    	franz.save()

    	if (franz.hasErrors()) {
    		println lucky.errors
    	}

    	def fluffy = new Pet(
    		owner:chet,
    		name:"Fluffy",
    		breed:"Shih-Tzu",
    		notes:null,
    		dateOfBirth:null
    		)
    	fluffy.save()

    	if (fluffy.hasErrors()) {
    		println lucky.errors
    	}

    	//  ****************************************** Create Rates
    	def weekday = new Rate(
    		name:"Weekday",
    		amount:12.00
    		)
    	weekday.save()

    	if (weekday.hasErrors()) {
    		println weekday.errors
    	}

    	def weekend = new Rate(
    		name:"Weekend/Holiday",
    		amount:15.00
    		)
    	weekend.save()

    	if (weekend.hasErrors()) {
    		println weekend.errors
    	}


    	//  ****************************************** Create Reservations
    	//  ****************************************** Create Payments
    	//  ****************************************** Create Contacts
    	//  ****************************************** Create VaccinationTypes
    	//  ****************************************** Create Vaccinations

    }
    def destroy = {
    }



}

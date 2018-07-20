package com.fhds.driver;

import com.fhds.model.business.manager.DAOManager;
import com.fhds.model.domain.Car;
import com.fhds.model.domain.Customer;
import com.fhds.model.domain.Itinerary;
import com.fhds.model.domain.RentalComposite;
import org.apache.log4j.Logger;

/*-
 * Please note that the FleetRental application can be run either by running the
 * ViewDriver class or this class. This class tests the Controller and Model
 * components.
 *
 * Its common to have test drivers such as this class to test applications.
 *
 * A real world implementation, would of course have a View (either Swing or Web
 * based).
 *
 * Runtime(VM Options) Setup to load application properties:
 * =========================================================
 *
 * Need to pass property file that is loaded by PropertyManager 
 * 1. From IDE, set the VM Options under Project Properties 
 *
 * -Dprop_location=<path>\config\application.properties 
 * 
 * 2. If using Ant/Build.xml: reads off from the target to run the app with the 
 * sys property set as : 
 *
 * <sysproperty key="prop_location" value="${config.dir}application.properties"/>
 *
 * Runtime(VM Options) Setup to load log4j properties:
 * ====================================================
 * 
 * Need to pass log4j configuration file that is loaded by Log4JInit 
 * 1. From IDE, set the VM Options under Project Properties
 *
 * -Dlog4j_prop_location=<path>\config\log4j.properties 
 *
 * 2. Build.xml: reads off from the target to run the app with the sys property 
 *  set as :
 *
 * <sysproperty key="log4j_prop_location" value="${config.dir}log4j.properties"/>
 *
 *
 * @author Mike.Prasad
 *
 */
public class TestDriver {

    /*
     * Category set in config/log4j.properties as
     * log4j.category.com.classexercise=DEBUG, A1
     */
    static Logger log = Logger.getLogger("com.fleetrentalhibernatedaosample");

    public static void main(String[] args) {
        Log4JInit.initializeLog4J();

        // In the real world implementation, customer would identify
        // an Itinerary.
        RentalComposite rentalComposite = new RentalComposite();

        // lets create a sample itinerary.
        // Itinerary constructor needs following fields : 
        // (fleetRentalPickUp, fleetRentalDropOff,pickUpMonth,pickUpDay,pickUpYear,pickUpTime,dropOffMonth,dropOffDay, dropOffYear,dropOffTime)		
        rentalComposite.setItinerary(new Itinerary("San Francisco Airport", "San Francisco Airport", "06", "18", "2006", "01:10", "06", "28", "2006", "12:00"));

        log.info("\n----------------");
        log.info("\n-->Checking Car Availability for itinerary: \n\n" + rentalComposite.getItinerary());

        // now that we have an itinerary, lets call into the Model via the controller,
        // to see if have any cars available for this itinerary
        DAOManager daoManager = DAOManager.getInstance();
        boolean status = daoManager.performAction("ProcessItinerary", rentalComposite);

        if (status) //if true then request processed successfully
        {
            // Lets check if cars are available if so we can reserve them.
            if (rentalComposite != null) {
                if (rentalComposite.getAvailableRentals() != null) {
                    if (rentalComposite.getAvailableRentals().isAvailable()) {
                        // Cool, we have a car to rent, lets get Customer info and the Car customer
                        // wants to rent.
                        log.info("\n-->Cars available for above itinerary: \n\n" + rentalComposite.getAvailableRentals());

                        // User enters personal info
                        // Customer contructor takes in lastname, firstname, email address, day time phone and evening phone
                        rentalComposite.setCustomer(new Customer("Simpson", "Homer", "homer@duff.com", "303-786-1111", "303-786-1111"));

                        // User select the car he/she wants to rent
                        // Car constructor takes in rate, manufacturer, model, miles included
                        rentalComposite.setRentedCar(new Car(25.50f, "Ford", "Focus", "Unlimited"));

                        log.info("\n-->Calling reserve rental car service with this details: \n\n" + rentalComposite);

                        // Ideally the type of the service that needs to be executed
                        // is mapped in a properties file. Hardcoded here to
                        // illustrate the example.
                        boolean rentalStatus = daoManager.performAction("ReserveRental", rentalComposite);
                    } else {
                        // Hopefully this doesn't happen in the real world! :)
                        log.info("No car available! Suggest hitchhiking!");
                    } //end if
                } else {
                    // AvailableRentals is NULL - this due to SQL Exception issue
                    log.error("We are facing an issue, please try back later!");
                }//end if								
            } //end if							
        } else {
            log.error("We are facing an issue, please try back later!");
        }

    } //end main

} //end class TestDriver

/* -
 run:
 Successfully configured log4j
INFO - 
----------------
INFO - 
-->Checking Car Availability for itinerary: 

fleetRentalPickUp Id:null
fleetRentalPickUp City:San Francisco Airport
fleetRentalDropOff Id:null
fleetRentalDropOff City:San Francisco Airport
pickUpMonth :06
pickUpDay :18
pickUpYear :2006
pickUpTime :01:10
dropOffMonth :06
dropOffDay :28
dropOffYear :2006
dropOffTime :12:00
qtyRentalDays :10
INFO - -------------------------------
INFO - Using Hibernate Implementation
INFO - -------------------------------
INFO - Available Rentals
INFO - ******Fetching Hibernate Session
DEBUG - Logging Provider: org.jboss.logging.Log4jLoggerProvider
INFO - HHH000397: Using ASTQueryTranslatorFactory
INFO - About to display the queried list
Hibernate: select carset1_.idCAR as idCAR2_0_, carset1_.engine_type as engine_t3_0_, carset1_.LOCATION_FK as LOCATION9_0_, carset1_.manufacturer as manufact4_0_, carset1_.miles_included as miles_in5_0_, carset1_.model as model6_0_, carset1_.rate as rate7_0_, carset1_.rented as rented8_0_ from location location0_ inner join cars carset1_ on location0_.idLOCATION=carset1_.LOCATION_FK and carset1_.discriminator='C' where carset1_.rented=?
INFO - Inside buildAvailableRentals
INFO - 
-->Cars available for above itinerary: 

Rental is available
State Tax: 6.89
Available Rentals List: 	

Car[	

SuperCar[		
carId = 1		
engineType = Korea]
		
manufacturer = Hyundai		
milesIncluded = Unlimited		
model = Accent		
rate = 23.5		
rented = N]
	

Car[	

SuperCar[		
carId = 2		
engineType = Nippon]
		
manufacturer = Toyota		
milesIncluded = Unlimited		
model = Camry		
rate = 23.5		
rented = N]

INFO - 
-->Calling reserve rental car service with this details: 


Customer Info :

lastname :Simpson
firstname :Homer
email address :homer@duff.com
day time phone :303-786-1111
evening Phone :303-786-1111

Available Rentals :
Rental is available
State Tax: 6.89
Available Rentals List: 	

Car[	

SuperCar[		
carId = 1		
engineType = Korea]
		
manufacturer = Hyundai		
milesIncluded = Unlimited		
model = Accent		
rate = 23.5		
rented = N]
	

Car[	

SuperCar[		
carId = 2		
engineType = Nippon]
		
manufacturer = Toyota		
milesIncluded = Unlimited		
model = Camry		
rate = 23.5		
rented = N]


Itinerary :
fleetRentalPickUp Id:null
fleetRentalPickUp City:San Francisco Airport
fleetRentalDropOff Id:null
fleetRentalDropOff City:San Francisco Airport
pickUpMonth :06
pickUpDay :18
pickUpYear :2006
pickUpTime :01:10
dropOffMonth :06
dropOffDay :28
dropOffYear :2006
dropOffTime :12:00
qtyRentalDays :10

Rented Car :
	

Car[	

SuperCar[		
carId = null		
engineType = null]
		
manufacturer = Ford		
milesIncluded = Unlimited		
model = Focus		
rate = 25.5		
rented = null]

ERROR - 
 Reservation Implementation not complete
 */

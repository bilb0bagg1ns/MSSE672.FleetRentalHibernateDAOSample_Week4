package com.fhds.driver;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fhds.model.domain.Car;
import com.fhds.model.domain.Location;
import com.fhds.model.services.factory.HibernateSessionFactory;

/**
 * This *DOES NOT* test all the layers. This is just a quick standalone class
 * that tests Hibernate functionality.
 *
 * TestDriver is the class that tests all the layers and exercises
 * FleetRentalHibernateDaoImpl
 *
 * @author Mike Prasad
 */
public class HibernateDriver {


    /*
     * Category set in config/log4j.properties as
     * log4j.category.com.classexercise=DEBUG, A1
     */
    static Logger log = Logger.getLogger("com.fleetrentalhibernatedaosample");

    /**
     * @param args
     */
    public static void main(String[] args) {
        Transaction tx = null;
        try {
            // initialize log4j
            Log4JInit.initializeLog4J();

            log.info("About to create a Hibernate Session");
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction(); // begin transaction

            /*
             * Create couple of Cars and add it to a location
             */
            log.info("---------------------------");
            log.info("Creating a couple of Cars and adding to a Location!");

            // Car(float rate, String manufacturer, String model, String milesIncluded, String rented)
            Car car1 = new Car("USA", 25.50f, "Ford", "Focus", "Unlimited", "N");
            Car car2 = new Car("Korea", 35.50f, "Hyundai", "Tiburon", "Unlimited", "N");
            Car car3 = new Car("Canada", 15.50f, "Geo", "Metro", "Unlimited", "Y");

            // create a new Location and add the above cars to it
            Location location = new Location("San Francisco");
            location.addCar(car1);
            location.addCar(car2);
            location.addCar(car3);

            log.info("About to save the created Cars and its Location!");
            session.save(location);
            tx.commit();
            log.info("Location with its Cars saved. Check database for data!");
            log.info("---------------------------");

            // Lets retrieve all the cars at San Francisco Location
            log.info("---------------------------");
            log.info("About to retrieve all cars at San Francisco Location!");

            log.info("About to create Query");
            /**
             * Note: The reference to Location in the query is the object
             * Location not the table location
             */
            Query q = session.createQuery("from Location l where l.locationId = 1");

            log.info("About to display the queried list");

            List locList = q.list();
            for (Object o : locList) {
                log.info((Location) o);
            }

            log.info("---------------------------");

            // Lets retrieve all the cars at San Francisco Location which are not rented
            log.info("---------------------------");
            log.info("About to retrieve all cars at San Francisco Location that are not rented!");

            log.info("About to create Query");
            /**
             * Note: The reference to Location in the query is the object
             * Location not the table location
             */
            q = session.createQuery("select car from Location l join l.carSet car where car.rented= :rented ");
            q.setString("rented", "N");

            log.info("About to display the queried list");
            List carList = q.list();
            for (Object o : carList) {
                log.info((Car) o);
            }

            log.info("---------------------------");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error(e.getClass() + ": " + e.getMessage(), e);
        } finally {
            HibernateSessionFactory.closeSessionAndFactory();
        }
    } //end main
}

/*-

RESULT OF RUNNING THIS DRIVER 
=============================

run:
Successfully configured log4j

INFO - About to create a Hibernate Session
DEBUG - Logging Provider: org.jboss.logging.Log4jLoggerProvider
INFO - ---------------------------
INFO - Creating a couple of Cars and adding to a Location!
INFO - About to save the created Cars and its Location!
Hibernate: insert into location (CITY) values (?)
INFO - Location with its Cars saved. Check database for data!
INFO - ---------------------------
INFO - ---------------------------
INFO - About to retrieve all cars at San Francisco Location!
INFO - About to create Query
INFO - HHH000397: Using ASTQueryTranslatorFactory
INFO - About to display the queried list
Hibernate: select location0_.idLOCATION as idLOCATI1_1_, location0_.CITY as CITY2_1_ from location location0_ where location0_.idLOCATION=1
Hibernate: select carset0_.LOCATION_FK as LOCATION9_0_0_, carset0_.idCAR as idCAR2_0_0_, carset0_.idCAR as idCAR2_0_1_, carset0_.engine_type as engine_t3_0_1_, carset0_.LOCATION_FK as LOCATION9_0_1_, carset0_.manufacturer as manufact4_0_1_, carset0_.miles_included as miles_in5_0_1_, carset0_.model as model6_0_1_, carset0_.rate as rate7_0_1_, carset0_.rented as rented8_0_1_ from cars carset0_ where carset0_.LOCATION_FK=?
INFO - 

Location[	
city = San Francisco	
locationId = 1	

Car[	

SuperCar[		
carId = 1		
engineType = Korea]
		
manufacturer = Hyundai		
milesIncluded = Unlimited		
model = Accent		
rate = 23.5		
rented = N]
	
]
INFO - ---------------------------
INFO - ---------------------------
INFO - About to retrieve all cars at San Francisco Location that are not rented!
INFO - About to create Query
INFO - About to display the queried list
Hibernate: select carset1_.idCAR as idCAR2_0_, carset1_.engine_type as engine_t3_0_, carset1_.LOCATION_FK as LOCATION9_0_, carset1_.manufacturer as manufact4_0_, carset1_.miles_included as miles_in5_0_, carset1_.model as model6_0_, carset1_.rate as rate7_0_, carset1_.rented as rented8_0_ from location location0_ inner join cars carset1_ on location0_.idLOCATION=carset1_.LOCATION_FK and carset1_.discriminator='C' where carset1_.rented=?
INFO - 	

Car[	

SuperCar[		
carId = 1		
engineType = Korea]
		
manufacturer = Hyundai		
milesIncluded = Unlimited		
model = Accent		
rate = 23.5		
rented = N]

INFO - 	

Car[	

SuperCar[		
carId = 2		
engineType = Nippon]
		
manufacturer = Toyota		
milesIncluded = Unlimited		
model = Camry		
rate = 23.5		
rented = N]

INFO - ---------------------------
 */

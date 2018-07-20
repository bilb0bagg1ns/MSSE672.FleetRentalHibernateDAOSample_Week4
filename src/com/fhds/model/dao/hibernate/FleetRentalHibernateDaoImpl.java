package com.fhds.model.dao.hibernate;

import com.fhds.model.dao.IFleetRentalDao;
import com.fhds.model.domain.AvailableRentals;
import com.fhds.model.domain.Car;
import com.fhds.model.domain.RentalComposite;
import com.fhds.model.services.factory.HibernateSessionFactory;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * This is where the Hibernate Code goes into.
 *
 * To this DAO, need to make sure the data is setup as discussed in the slides.
 *
 * Exceptions are not handled here and are left as a student exercise.
 *
 * Any exception(s) that happen here are to be propagated appropriately to the
 * calling tiers.
 *
 * @author Mike.Prasad
 *
 *
 */
public class FleetRentalHibernateDaoImpl implements IFleetRentalDao {

    /*
     * Category set in config/log4j.properties as
     * log4j.category.com.classexercise=DEBUG, A1
     */
    static Logger log = Logger.getLogger("com.fleetrentalhibernatedaosample");

    /**
     * Retrieve all the vehicles that are available based on criteria in the
     * Itinerary object and populate AvailableRentals
     * @param rentalComposite
     * @return 
     */
    @Override
    public boolean getAvailableRentals(RentalComposite rentalComposite) {
        boolean status = false;

        log.info("-------------------------------");
        log.info("Using Hibernate Implementation");
        log.info("-------------------------------");

        log.info("Available Rentals");
        AvailableRentals availableRentals = null;

        //Session session = fetchSession();
        availableRentals = fetchAvailableCars();

        if (availableRentals != null) {
            // indicate that rentals are available for customer's request
            availableRentals.setAvailable(true);
            availableRentals.setStateTax(6.89f);
            // set available rentals into the rental composite
            rentalComposite.setAvailableRentals(availableRentals);

            // set the return status
            status = true;
        }
        return status;
    } //end getAvailableRentals

    /**
     * Student exercise to add the relevant JDBC code.
     * @param _rentalComposite
     * @return 
     */
    @Override
    public boolean reserveRentalCar(RentalComposite _rentalComposite) {
        boolean status = false;
        log.error("\n Reservation Implementation not complete");
        return status;
    }

    /**
     * Gets a hibernate session from HibernateSessionFactory
     *
     * @return org.hibernate.Session
     *
     */
    private Session fetchSession() {
        log.info("******Fetching Hibernate Session");

        Session session = HibernateSessionFactory.currentSession();

        return session;

    } //end fetchConnection

    /**
     * Fetches all available cars for rental
     *
     * @return List containing all available rental cars
     */
    private AvailableRentals fetchAvailableCars() {
        AvailableRentals availableRentals = null;
        Transaction tx = null;
        List<Car> carList = null;
        try {
            Session session = fetchSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("select car from Location l join l.carSet car where car.rented= :rented ");
            query.setString("rented", "N");

            log.info("About to display the queried list");

            carList = query.list();
            availableRentals = buildAvailableRentals(carList);

            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            log.error(e.getClass() + ": " + e.getMessage(), e);
        } finally {
            HibernateSessionFactory.closeSessionAndFactory();
        }
        return availableRentals;

    } // fetchAvailableCars

    /**
     * Iterate over the List and build out the AvailableRentals object.
     *
     * @param List containing the available rentals
     *
     * @return AvailableRentals
     */
    private AvailableRentals buildAvailableRentals(List<Car> carList) {
        log.info("Inside buildAvailableRentals");
        AvailableRentals availableRentals = new AvailableRentals();

        for (Car car : carList) {
            availableRentals.addRental(car);
        }
        return availableRentals;
    } // end buildAvailableRentals

} // end class FleetRentalHibernateDaoImpl

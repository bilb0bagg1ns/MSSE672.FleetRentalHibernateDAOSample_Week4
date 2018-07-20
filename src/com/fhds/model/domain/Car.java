package com.fhds.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This class extends SuperCar just to demonstrate how to persist and map in
 * Hibernate, domain objects that are associated via a 'IsA' relationship.
 *
 * @author Mike.Prasad
 *
 */
@Entity
@Table(name = "cars")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="discriminator",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue(value="C")
public class Car extends SuperCar implements Serializable {


    /**
     * Daily rate
     */
    private float rate;

    /**
     * Car manufacturer
     */
    private String manufacturer;

    /**
     * Car model
     */
    private String model;

    /**
     * Free miles included in this rental
     */
    private String milesIncluded;

    /**
     * Y/N : Rented/Not Rented
     */
    private String rented;

    /**
     * Location where this car is available
     */
    private Location location;

    /**
     *
     */
    public Car() {

        // TODO Auto-generated constructor stub
    }

    /**
     * @param rate
     * @param manufacturer
     * @param model
     * @param milesIncluded
     */
    public Car(float rate, String manufacturer, String model, String milesIncluded) {
        super();
        this.rate = rate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.milesIncluded = milesIncluded;
    }

    public Car(String engineType, float rate, String manufacturer, String model, String milesIncluded, String rented) {

        // initialize parent
        super(engineType);

        this.rate = rate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.milesIncluded = milesIncluded;
        this.rented = rented;
    }

    public Car(float rate, String manufacturer, String model, String milesIncluded, String rented) {
        super();
        // TODO Auto-generated constructor stub
        this.rate = rate;
        this.manufacturer = manufacturer;
        this.model = model;
        this.milesIncluded = milesIncluded;
        this.rented = rented;
    }

    /**
     * @return Returns the rate.
     */
    @Column(name = "rate", precision = 12)
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    /**
     * @return Returns the manufacturer.
     */
    @Column(name = "manufacturer", length = 40)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return Returns the model.
     */
    @Column(name = "model", length = 20)    
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return Returns the milesIncluded.
     */
    @Column(name = "miles_included", length = 20)
    public String getMilesIncluded() {
        return milesIncluded;
    }

    public void setMilesIncluded(String milesIncluded) {
        this.milesIncluded = milesIncluded;
    }

    @Column(name = "rented", length = 1)
    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_FK", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return @author
     */
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t\n\nCar[");
        stringBuilder.append(super.toString());
        stringBuilder.append("\t\t\nmanufacturer = ").append(manufacturer);
        stringBuilder.append("\t\t\nmilesIncluded = ").append(milesIncluded);
        stringBuilder.append("\t\t\nmodel = ").append(model);
        stringBuilder.append("\t\t\nrate = ").append(rate);
        stringBuilder.append("\t\t\nrented = ").append(rented);
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }
    
    

} //end Car

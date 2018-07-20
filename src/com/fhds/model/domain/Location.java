package com.fhds.model.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location implements java.io.Serializable {

    /**
     * To uniquely identify this location.
     */
    private Integer locationId;

    /**
     * City name of this location
     */
    private String city;

    /**
     * Holds the cars that are available in this location As part of the
     * Hibernate mapping that there are one-to-many association between a
     * Location and a Car.
     *
     */
    private Set<Car> carSet = new HashSet<>(0);

    public Location() {
    }

    public Location(String city) {
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idLOCATION", unique = true, nullable = false)
    public Integer getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Column(name = "CITY", length = 20)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carsSet) {
        this.carSet = carsSet;
    }

    /**
     * Set the location of the car to this location and add car to the Set.
     *
     * @param car
     */
    public void addCar(Car car) {
        car.setLocation(this);
        carSet.add(car);
    }

    /**
     *
     * @return @author
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\nLocation[");
        stringBuilder.append("\t\ncity = ").append(city);
        stringBuilder.append("\t\nlocationId = ").append(locationId);
        for (Car c : carSet) {
            stringBuilder.append(c);
        }
        stringBuilder.append("\t\n]");

        return stringBuilder.toString();
    }

}

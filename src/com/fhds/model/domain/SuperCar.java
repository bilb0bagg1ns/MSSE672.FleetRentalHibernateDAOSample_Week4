package com.fhds.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is introduced just to demonstrate how to persist and map in
 * Hibernate, domain objects that are associated via a 'IsA' relationship.
 *
 * @author Mike.Prasad
 * 
 */
@Entity
@Table(name = "cars")
@DiscriminatorColumn(name="discriminator")
@DiscriminatorValue("S")
public class SuperCar implements Serializable {

    /**
     * Car ID
     */
    private Integer carId;

    /**
     * Indicates country of origin of engine
     */
    String engineType;

    public SuperCar() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SuperCar(String engineType) {
        super();
        this.engineType = engineType;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idCAR", unique = true, nullable = false)
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    @Column(name = "engine_type", length = 20)
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t\n\nSuperCar[");
        stringBuilder.append("\t\t\ncarId = ").append(carId);
        stringBuilder.append("\t\t\nengineType = ").append(engineType);
        stringBuilder.append("]\n");
        return stringBuilder.toString();
    }

}

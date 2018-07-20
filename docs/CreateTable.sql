REM ********************************************************************
PROMPT Removing 'LOCATION' database object
REM ********************************************************************

drop table LOCATION;


REM ********************************************************************
PROMPT Creating relational table 'LOCATION' that will hold the cars in the 
PROMPT Fleet inventory
REM ********************************************************************

CREATE TABLE LOCATION (
  idLOCATION INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  CITY VARCHAR(20) NULL,
  PRIMARY KEY(idLOCATION)  
);

REM ********************************************************************
PROMPT Inserting sample data into relational table 'LOCATION' ...
REM ********************************************************************

INSERT INTO regis.LOCATION (idLocation, city) VALUES(
1, "San Francisco"); 

REM ********************************************************************
PROMPT Removing 'CARS' database object
REM ********************************************************************

drop table CARS;

REM ********************************************************************
PROMPT Inserting sample data into relational table 'CARS'. There is one-to-many relationship between LOCATION to CARS
REM ********************************************************************


CREATE TABLE CARS (
  idCAR INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  LOCATION_FK INTEGER UNSIGNED NOT NULL,
  engine_type VARCHAR(20) NULL,
  rate FLOAT NULL,
  manufacturer VARCHAR(40) NULL,
  model VARCHAR(20) NULL,
  miles_included VARCHAR(20) NULL,
  rented CHAR NULL,
  discriminator VARCHAR(20) NOT NULL,
  PRIMARY KEY(idCAR),
  INDEX CARS_FKIndex1(LOCATION_FK),
  FOREIGN KEY(LOCATION_FK)
    REFERENCES LOCATION(idLOCATION)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

REM ********************************************************************
PROMPT Inserting sample data into relational table 'CAR' ...
REM ********************************************************************

INSERT INTO regis.CARS (LOCATION_FK, engine_type, rate, manufacturer, model, miles_included, rented, discriminator) VALUES(
1, "Korea", 23.50, "Hyundai", "Accent", "Unlimited", "N", "C");

INSERT INTO regis.CARS (LOCATION_FK, engine_type, rate, manufacturer, model, miles_included, rented, discriminator) VALUES(
2, "Nippon", 23.50, "Toyota", "Camry", "Unlimited", "N", "C");

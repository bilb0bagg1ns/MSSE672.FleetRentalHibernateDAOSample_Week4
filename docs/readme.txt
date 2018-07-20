================================================
FleetRentalHibernateDAOSample Highlights
================================================
This sample is an advanced Hibernate implementation, with the following features:

1. Many to one relationship between Location and Cars with a Location holding a number of Cars.
2. Car/SuperCar inheritance hierarchy.
3. The aforementioned inheritance hierarchy of the domain class, requiring the following:
     a. adding a discriminator column to Cars table
     b. Need for use of additional annotations(@Inheritance, @DiscriminatorColumn, @DiscriminatorValue) in the 
domain classes (Car and SuperCar)

NOTE: In this sample, I've instrumented Car/SuperCar, and Location with annotations 
and hence demonstrating persistence of these classes only with Hibernate.

===========
Environment 
===========
This sample was tested using on a Windows box using following libraries:

1. hibernate-release-5.3.2.Final 
2. log4j-1.2.15
3. mysql-5.7.12

================================================
Steps to run the FleetRentalHibernateDAOSample
================================================

1. Assumes MySQL is installed and running.
    - To to MySQL bin folder and run 'mysqld'
2. Run the sql in CreateTable.sql - to create the table.
3. Study and run the HibernateDriver 
    -  This *DOES NOT* test all the layers. This is just a quick standalone class that tests Hibernate functionality.
4. Study and run the TestDriver
    - This class tests all the layers and exercises FleetRentalHibernateDaoImpl

========================
 Additional Samples
=======================
 1. http://www.mkyong.com/hibernate/hibernate-one-to-many-relationship-example-annotation/
 2. http://viralpatel.net/blogs/hibernate-inheritence-table-per-hierarchy-mapping/
 
package com.fhds.model.domain;

import java.io.Serializable;
import java.util.Calendar;


public class Itinerary implements Serializable
{

  /** Pickup City Id */
  private String fleetRentalPickUpCityId; 

  /** Pickup City */
  private String fleetRentalPickUpCity; 

  /** Return City Id */
  private String fleetRentalDropOffCityId;

  /** Return City */
  private String fleetRentalDropOffCity;

  /** Pick-up Date */
  private String pickUpMonth;
  private String pickUpDay;
  private String pickUpYear;
  
  /** Pick-up Time */
  private String pickUpTime;
  
  /** Drop-off Date */
  private String dropOffMonth;
  private String dropOffDay;
  private String dropOffYear;
  
  /** Drop-off Time */
  private String dropOffTime;

  /** */ 
  private int qtyRentalDays = 0;
  
	/**
	 *
	 */
	public Itinerary() {

		// TODO Auto-generated constructor stub
	}


    public Itinerary(String fleetRentalPickUp, 
			         String fleetRentalDropOff,
			         String pickUpMonth,
			         String pickUpDay,
			         String pickUpYear,
			         String pickUpTime,
			         String dropOffMonth,
			         String dropOffDay, 
			         String dropOffYear,
			         String dropOffTime)
	{
		   this.fleetRentalPickUpCity = fleetRentalPickUp;
		   this.fleetRentalDropOffCity = fleetRentalDropOff;
		   this.pickUpMonth = pickUpMonth;
		   this.pickUpDay = pickUpDay;
		   this.pickUpYear = pickUpYear;
		   this.pickUpTime = pickUpTime;
		   this.dropOffMonth = dropOffMonth;
		   this.dropOffDay = dropOffDay;
		   this.dropOffYear = dropOffYear;
		   this.dropOffTime = dropOffTime;
		   
		   // determine qty of rental days by using Calendar class.		   
		   Calendar pickUpCalendar = Calendar.getInstance();
		   
		   // -1 from pickUpMonth since Calendar is 0 based (Jan is 0)
		   pickUpCalendar.set(Integer.parseInt(pickUpYear), 
				              (Integer.parseInt(pickUpMonth)-1), 
							  Integer.parseInt(pickUpDay));

		   Calendar dropOffCalendar = Calendar.getInstance();
		   
		   // -1 from pickUpMonth since Calendar is 0 based (Jan is 0)
		   dropOffCalendar.set(Integer.parseInt(dropOffYear), 
				              (Integer.parseInt(dropOffMonth)-1), 
							  Integer.parseInt(dropOffDay));

		   // convert pickUp and dropOff dates into milli seconds,
		   // so we can determine the qty of rental days.
		   long diffDayMillis =   dropOffCalendar.getTimeInMillis() 
		                        - pickUpCalendar.getTimeInMillis();		   

		   // divide by milli seconds in a day (24*60*60*1000)
		   qtyRentalDays = (int)(diffDayMillis/86400000);
		   
		   
	}

	/**
	 * @return Returns the dropOffDay.
	 */
	public String getDropOffDay() {
		return dropOffDay;
	}


	/**
	 * @return Returns the dropOffMonth.
	 */
	public String getDropOffMonth() {
		return dropOffMonth;
	}


	/**
	 * @return Returns the dropOffTime.
	 */
	public String getDropOffTime() {
		return dropOffTime;
	}


	/**
	 * @return Returns the dropOffYear.
	 */
	public String getDropOffYear() {
		return dropOffYear;
	}


	/**
	 * @return Returns the fleetRentalDropOff.
	 */
	public String getFleetRentalDropOffCityId() {
		return fleetRentalDropOffCityId;
	}


	/**
	 * @return Returns the fleetRentalDropOffCity.
	 */
	public String getFleetRentalDropOffCity() {
		return fleetRentalDropOffCity;
	}


	/**
	 * @param fleetRentalDropOffCity The fleetRentalDropOffCity to set.
	 */
	public void setFleetRentalDropOffCity(String fleetRentalDropOffCity) {
		this.fleetRentalDropOffCity = fleetRentalDropOffCity;
	}


	/**
	 * @param fleetRentalPickUpCity The fleetRentalPickUpCity to set.
	 */
	public void setFleetRentalPickUpCity(String fleetRentalPickUpCity) {
		this.fleetRentalPickUpCity = fleetRentalPickUpCity;
	}


	/**
	 * @return Returns the fleetRentalPickUp.
	 */
	public String getFleetRentalPickUpCityId() {
		return fleetRentalPickUpCityId;
	}


	/**
	 * @return Returns the fleetRentalPickUpCity.
	 */
	public String getFleetRentalPickUpCity() {
		return fleetRentalPickUpCity;
	}


	/**
	 * @return Returns the pickUpDay.
	 */
	public String getPickUpDay() {
		return pickUpDay;
	}


	/**
	 * @return Returns the pickUpMonth.
	 */
	public String getPickUpMonth() {
		return pickUpMonth;
	}


	/**
	 * @return Returns the pickUpTime.
	 */
	public String getPickUpTime() {
		return pickUpTime;
	}


	/**
	 * @return Returns the pickUpYear.
	 */
	public String getPickUpYear() {
		return pickUpYear;
	}


	/**
	 * @return Returns the qtyRentalDays.
	 */
	public int getQtyRentalDays() {
		return qtyRentalDays;
	}


	@Override
	public String toString()
	{
	  StringBuilder stringBuilder = new StringBuilder();
	  stringBuilder.append ("fleetRentalPickUp Id:");
	  stringBuilder.append (fleetRentalPickUpCityId);
	  stringBuilder.append ("\nfleetRentalPickUp City:");
	  stringBuilder.append (fleetRentalPickUpCity);
	  stringBuilder.append ("\nfleetRentalDropOff Id:");
	  stringBuilder.append (fleetRentalDropOffCityId);
	  stringBuilder.append ("\nfleetRentalDropOff City:");
	  stringBuilder.append (fleetRentalDropOffCity);
	  stringBuilder.append ("\npickUpMonth :");
	  stringBuilder.append (pickUpMonth);
	  stringBuilder.append ("\npickUpDay :");
	  stringBuilder.append (pickUpDay);
	  stringBuilder.append ("\npickUpYear :");
	  stringBuilder.append (pickUpYear);
	  stringBuilder.append ("\npickUpTime :");
	  stringBuilder.append (pickUpTime);
	  stringBuilder.append ("\ndropOffMonth :");
	  stringBuilder.append (dropOffMonth);
	  stringBuilder.append ("\ndropOffDay :");
	  stringBuilder.append (dropOffDay);
	  stringBuilder.append ("\ndropOffYear :");
	  stringBuilder.append (dropOffYear);
	  stringBuilder.append ("\ndropOffTime :");
	  stringBuilder.append (dropOffTime);
	  stringBuilder.append ("\nqtyRentalDays :");
	  stringBuilder.append (qtyRentalDays);
	
	  return stringBuilder.toString();
	}


} //end Itinerary
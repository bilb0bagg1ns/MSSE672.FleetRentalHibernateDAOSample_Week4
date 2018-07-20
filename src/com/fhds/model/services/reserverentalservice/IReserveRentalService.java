/**
 * 
 */
package com.fhds.model.services.reserverentalservice;

import com.fhds.model.domain.RentalComposite;
import com.fhds.model.services.IService;

/**
 * @author mike.prasad
 *
 */
public interface IReserveRentalService extends IService
{

	public final String NAME = "IReserveRentalService";

	/** Register customer into our application 
	 * @throws RegistrationException */
	public boolean reserveRentalCar(RentalComposite rentalComposite);

}

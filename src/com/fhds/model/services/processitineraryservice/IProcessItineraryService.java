/**
 *
 */
package com.fhds.model.services.processitineraryservice;

import com.fhds.model.domain.RentalComposite;
import com.fhds.model.services.IService;

/**
 * @author mike.prasad
 *
 */
public interface IProcessItineraryService extends IService {

    public final String NAME = "IProcessItineraryService";

    public boolean processItinerary(RentalComposite rentalComposite);
}

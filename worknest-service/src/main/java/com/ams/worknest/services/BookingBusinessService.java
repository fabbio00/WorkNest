package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.resources.BookingBusinessCreateResource;
import com.ams.worknest.model.resources.BookingBusinessFindResource;

import java.util.UUID;

/**
 * BookingBusinessService interface.
 * This interface handles the business logic for the BookingBusiness entity.
 */
public interface BookingBusinessService {

    /**
     * Creates a new booking business.
     *
     * @param bookingBusinessCreateDto The details of the booking business to be created.
     * @return A resource representing the created booking business.
     */
    BookingBusinessCreateResource createBookingBusiness(BookingBusinessCreateDto bookingBusinessCreateDto);

    /**
     * Finds a booking business by its unique identifier.
     *
     * @param bookingBusinessId The unique identifier of the booking business.
     * @return A resource representing the found booking business.
     */
    BookingBusinessFindResource findBookingBusinessById(UUID bookingBusinessId);
}

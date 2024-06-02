package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingBusinessListDeleteDto;
import com.ams.worknest.model.entities.BookingBusiness;
import com.ams.worknest.model.resources.BookingBusinessResource;
import com.ams.worknest.model.resources.BookingDeleteResource;
import com.ams.worknest.model.resources.BookingFindByCompanyResource;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * BookingBusinessService interface.
 * This interface handles the business logic for the BookingBusiness entity.
 */
public interface BookingBusinessService {

    /**
     * Finds bookings by user id in booking business and optionally by workstation type.
     *
     * @param businessBookingId The id from the booking business table.
     * @param type The type of workstation to filter bookings by (optional).
     * @return A list of bookings associated with the given user id and workstation type.
     */
    List<BookingFindByCompanyResource> findBookingsByBusinessBookingIdAndType(UUID businessBookingId, String type);

    /**
     * Deletes bookings by their IDs.
     *
     * @param bookingIds The list of booking IDs to delete.
     * @return A list of BookingDeleteResource representing the result of the deletion.
     */
    List<BookingDeleteResource> cancelBookingsByIds(BookingBusinessListDeleteDto bookingIds);


    /**
     * Finds a booking business by its unique identifier.
     *
     * @param bookingBusinessId The unique identifier of the booking business.
     * @return A resource representing the found booking business.
     */
    BookingBusinessResource findBookingBusinessById(UUID bookingBusinessId);

    /**
     * Retrieves all BookingBusiness entities associated with a specific user.
     *
     * @param userId The id of the user.
     * @return A list of BookingBusiness entities associated with the given user id.
     */
    List<BookingBusinessResource> findBookingBusinessesByUserId(UUID userId, LocalDate startDate, LocalDate endDate);
}

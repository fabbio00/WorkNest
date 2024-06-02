package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.BookingBusiness;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * BookingBusinessRepository interface.
 * This interface handles the data access layer for the BookingBusiness entity.
 */
public interface BookingBusinessRepository extends JpaRepository<BookingBusiness, UUID> {

    /**
     * Custom query to find bookings by business booking id and optionally by workstation type.
     *
     * @param businessBookingId The business booking id.
     * @param type The type of workstation to search bookings (optional).
     * @return A list of bookings associated with the given business booking id and workstation type.
     */
    @Query("SELECT b FROM Booking b " +
            "JOIN b.bookingBusiness bb " +
            "WHERE bb.id = :businessBookingId AND (:type IS NULL OR b.workStation.type = :type)")
    List<Booking> findBookingsByBusinessBookingIdAndType(
            @Param("businessBookingId") UUID businessBookingId, @Param("type") String type
    );

    /**
     * Method to update the status of bookings to 'canceled' based on a list of booking IDs.
     *
     * @param ids The list of booking IDs to be updated.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = 'canceled' WHERE b.id IN :ids")
    void cancelBookingsByIds(@Param("ids") List<UUID> ids);

    /**
     * Custom query to find all BookingBusiness entities associated with a specific user.
     *
     * @param userId The id of the user.
     * @return A list of BookingBusiness entities associated with the given user id.
     */
    @Query("SELECT bb FROM BookingBusiness bb WHERE bb.user.id = :userId")
    List<BookingBusiness> findBookingBusinessesByUserId(@Param("userId") UUID userId);

}

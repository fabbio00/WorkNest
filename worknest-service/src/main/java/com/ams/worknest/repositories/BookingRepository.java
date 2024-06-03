package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * BookingRepository interface.
 * This interface handles the data access layer for the Booking entity.
 */
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    /**
     * Custom query to find bookings by start date only.
     *
     * @param date The start date to search bookings.
     * @return A list of bookings that start on the given date.
     */
    @Query(value = "SELECT * FROM booking WHERE DATE(start_date) = :date", nativeQuery = true)
    List<Booking> findByStartDateOnly(LocalDate date);

    /**
     * Finds bookings by user.
     *
     * @param user The user to search bookings.
     * @return A list of bookings associated with the given user.
     */
    List<Booking> findByUser(User user);

    List<Booking> findByBookingBusinessId(UUID bookingBusinessId);

    /**
     * Deletes bookings by booking business id.
     *
     * @param bookingBusinessId The booking business id to search bookings.
     */
    @Transactional
    @Modifying
    void deleteByBookingBusinessId(UUID bookingBusinessId);
}

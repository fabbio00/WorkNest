package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.dto.BookingEditDto;
import com.ams.worknest.model.resources.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * BookingService interface.
 * This interface handles the business logic for the Booking entity.
 */
public interface BookingService {

    /**
     * Creates a new booking.
     *
     * @param bookingCreateDto The data transfer object containing the details of the booking to be created.
     * @return A resource representing the created booking.
     */
    BookingCreateResource createBooking(BookingCreateDto bookingCreateDto);

    /**
     * Finds a booking by its unique identifier.
     *
     * @param bookingId The unique identifier of the booking.
     * @return A resource representing the found booking.
     */
    BookingFindResource findBookingById(UUID bookingId);

    /**
     * Finds bookings by date.
     *
     * @param date The date to search bookings.
     * @return A list of resources representing the bookings found.
     */
    List<BookingFindWorkStationResource> findBookingsByDate(LocalDate date);

    /**
     * Finds bookings by user id.
     *
     * @param userId The unique identifier of the user.
     * @return A list of resources representing the bookings found.
     */
    List<BookingFindByUserResource> findBookingsByUserId(UUID userId);

    /**
     * Deletes a booking.
     *
     * @param bookingId The unique identifier of the booking to be deleted.
     * @return A resource representing the deleted booking.
     */
    BookingDeleteResource deleteBooking(UUID bookingId);

    /**
     * Edits a booking.
     *
     * @param bookingId The unique identifier of the booking to be edited.
     * @param bookingEditDto The data transfer object containing the details of the booking to be edited.
     * @return A resource representing the edited booking.
     */
    BookingEditResource editBooking(UUID bookingId, BookingEditDto bookingEditDto);

    /**
     * Saves a list of bookings.
     *
     * @param bookingCreateDtos The list of data transfer objects containing the details of the bookings to be saved.
     * @return A list of resources representing the created bookings.
     */
    List<BookingCreateResource> saveBookings(List<BookingCreateDto> bookingCreateDtos);

    /**
     * Finds bookings by company id and optionally filters them by employee name and surname.
     *
     * @param companyId The unique identifier of the company.
     * @param employeeName The name of the employee to filter bookings by.
     * @param employeeSurname The surname of the employee to filter bookings by (optional).
     * @param startDate The start date of the period to filter bookings by (optional).
     * @param endDate The end date of the period to filter bookings by (optional).
     * @return A list of resources representing the bookings found.
     */
    List<BookingFindByCompanyResource> findBookingsByCompanyId(
            UUID companyId,
            String employeeName,
            String employeeSurname,
            LocalDate startDate,
            LocalDate endDate
    );
}

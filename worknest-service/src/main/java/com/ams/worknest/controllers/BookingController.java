package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.dto.BookingEditDto;
import com.ams.worknest.model.resources.*;
import com.ams.worknest.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Controller for managing bookings.
 * Provides endpoints for creating and retrieving booking details.
 */
@Slf4j
@RestController
@RequestMapping(value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    /**
     * Create a booking with the provided booking details.
     *
     * @param bookingCreateDto the booking data transfer object containing booking details
     * @return the created booking as a resource
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookingCreateResource bookingCreation(@RequestBody BookingCreateDto bookingCreateDto){
        return bookingService.createBooking(bookingCreateDto);
    }

    /**
     * Retrieve a booking by its unique identifier.
     *
     * @param bookingId the UUID of the booking to retrieve
     * @return the requested booking as a resource
     */
    @GetMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.OK)
    public BookingFindResource bookingFindById(@PathVariable("bookingId") UUID bookingId){
        return bookingService.findBookingById(bookingId);
    }

    /**
     * Retrieve bookings for a specific date.
     *
     * @param date the date for which bookings are to be retrieved
     * @return a list of bookings for the specified date
     */
    @GetMapping("/findDesks")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingFindWorkStationResource> getBookingsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return bookingService.findBookingsByDate(date);
    }

    /**
     * Retrieves a list of bookings associated with a specific user.
     *
     * @param userId the UUID of the user whose bookings are to be retrieved
     * @return a list of booking resources associated with the specified user
     */
    @GetMapping("/list/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingFindByUserResource> getBookingsByUserId(@PathVariable("userId") UUID userId){
        return bookingService.findBookingsByUserId(userId);
    }

    /**
     * Delete a booking with the specified ID.
     *
     * @param bookingId The UUID of the booking to delete
     * @return A {@link BookingDeleteResource} representing the deleted booking
     * @throws RuntimeException if the booking doesn't exist
     */
    @PutMapping("/delete/{bookingId}")
    @ResponseStatus(HttpStatus.OK)
    public BookingDeleteResource bookingDelete(@PathVariable("bookingId") UUID bookingId){
        return bookingService.deleteBooking(bookingId);
    }

    /**
     * Edits the details of a booking with the specified ID using the provided data and returns the updated booking details.
     *
     * @param bookingId      The UUID of the booking to be edited.
     * @param bookingEditDto The data containing the updated details for the booking.
     * @return The resource containing the updated details of the booking.
     * @throws RuntimeException if the booking with the specified ID is not found.
     */
    @PutMapping("/edit/{bookingId}")
    @ResponseStatus(HttpStatus.OK)
    public BookingEditResource bookingEdit(@PathVariable("bookingId") UUID bookingId, @RequestBody BookingEditDto bookingEditDto){
        return bookingService.editBooking(bookingId, bookingEditDto);
    }

    /**
     * Retrieves a list of bookings associated with a specific company.
     *
     * @param companyId the UUID of the company whose bookings are to be retrieved
     * @param employeeName the name of the employee (optional)
     * @param employeeSurname the surname of the employee (optional)
     * @param startDate the start date for the booking period (optional)
     * @param endDate the end date for the booking period (optional)
     * @return a list of booking resources associated with the specified company
     */
    @GetMapping("/list_by_company/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingFindByCompanyResource> getBookingsByCompanyId(
            @PathVariable("companyId") UUID companyId,
            @RequestParam(value = "employeeName", required = false) String employeeName,
            @RequestParam(value = "employeeSurname", required = false) String employeeSurname,
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return bookingService.findBookingsByCompanyId(companyId, employeeName, employeeSurname, startDate, endDate);
    }

    /**
     * Saves a list of bookings.
     * This endpoint accepts a list of booking creation DTOs, processes them to create multiple bookings,
     * and returns a list of resources representing the created bookings.
     *
     * @param bookingCreateDtos A list of {@link BookingCreateDto} objects containing the details for each booking to be created.
     * @return A list of {@link BookingCreateResource} objects representing the created bookings.
     */
    @PostMapping("/save-list")
    @ResponseStatus(HttpStatus.CREATED)
    public List<BookingCreateResource> saveBookings(@RequestBody List<BookingCreateDto> bookingCreateDtos){
        return bookingService.saveBookings(bookingCreateDtos);
    }
}

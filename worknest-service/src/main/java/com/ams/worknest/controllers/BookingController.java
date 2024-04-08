package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.model.resources.BookingFindByUserResource;
import com.ams.worknest.model.resources.BookingFindResource;
import com.ams.worknest.model.resources.BookingFindWorkStationResource;
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

}

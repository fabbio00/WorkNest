package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.dto.BookingBusinessListDeleteDto;
import com.ams.worknest.model.resources.BookingBusinessCreateResource;
import com.ams.worknest.model.resources.BookingBusinessResource;
import com.ams.worknest.model.resources.BookingDeleteResource;
import com.ams.worknest.model.resources.BookingFindByCompanyResource;
import com.ams.worknest.services.BookingBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Controller for managing business bookings.
 * Provides endpoints for retrieving booking details.
 */
@Slf4j
@RestController
@RequestMapping(value = "/booking-business", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookingBusinessController {

    /**
     * The booking business service.
     */
    private final BookingBusinessService bookingBusinessService;

    /**
     * Create a booking business with the provided booking business details.
     *
     * @param bookingBusinessCreateDto the booking business data transfer object containing the details of the booking business to be created
     * @return a booking business resource indicating the success of the operation
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookingBusinessCreateResource bookingBusinessCreation(@RequestBody BookingBusinessCreateDto bookingBusinessCreateDto){
        return bookingBusinessService.createBookingBusiness(bookingBusinessCreateDto);
    }

    /**
     * Retrieves a list of bookings associated with a specific user in booking business and optionally filters them by workstation type.
     *
     * @param businessBookingId The UUID of the business booking whose bookings are to be retrieved.
     * @param type The type of workstation to filter bookings by (optional).
     * @return A list of bookings associated with the specified business booking.
     */
    @GetMapping("/business_user/{businessBookingId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingFindByCompanyResource> getBookingsByUserIdAndType(
            @PathVariable("businessBookingId") UUID businessBookingId,
            @RequestParam(value = "type", required = false) String type) {
        return bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);
    }

    /**
     * Cancels a list of bookings by their IDs.
     *
     * @param bookingIds The list of booking IDs to cancel.
     * @return A list of BookingDeleteResource representing the result of the cancellation.
     */
    @PutMapping("/business_user/delete")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingDeleteResource> cancelBookingsByIds(@RequestBody BookingBusinessListDeleteDto bookingIds) {
        return bookingBusinessService.cancelBookingsByIds(bookingIds);
    }

    /**
     * Find a booking business with the provided booking business id.
     *
     * @param bookingBusinessId the booking business id
     * @return a booking business resource indicating the success of the operation
     */
    @GetMapping("/{bookingBusinessId}")
    @ResponseStatus(HttpStatus.OK)
    public BookingBusinessResource bookingBusinessFindById(@PathVariable("bookingBusinessId") UUID bookingBusinessId){
        return bookingBusinessService.findBookingBusinessById(bookingBusinessId);
    }

    /**
     * Retrieves a list of booking businesses associated with a specific user, optionally filtered by date range.
     *
     * @param userId The UUID of the user whose booking businesses are to be retrieved.
     * @param startDate The start date to filter bookings by (optional).
     * @param endDate The end date to filter bookings by (optional).
     * @return A list of booking businesses associated with the specified user.
     */
    @GetMapping("/list/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingBusinessResource> getBookingBusinessesByUserId(
            @PathVariable("userId") UUID userId,
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return bookingBusinessService.findBookingBusinessesByUserId(userId, startDate, endDate);
    }

}

package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.resources.BookingBusinessCreateResource;
import com.ams.worknest.model.resources.BookingBusinessFindResource;
import com.ams.worknest.services.BookingBusinessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for managing booking businesses.
 * Provides endpoints for creating and retrieving booking business details.
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
     * Find a booking business with the provided booking business id.
     *
     * @param bookingBusinessId the booking business id
     * @return a booking business resource indicating the success of the operation
     */
    @GetMapping("/{bookingBusinessId}")
    @ResponseStatus(HttpStatus.OK)
    public BookingBusinessFindResource bookingBusinessFindById(@PathVariable("bookingBusinessId") UUID bookingBusinessId){
        return bookingBusinessService.findBookingBusinessById(bookingBusinessId);
    }



}

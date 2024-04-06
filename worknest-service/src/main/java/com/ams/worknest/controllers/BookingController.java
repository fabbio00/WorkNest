package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.model.resources.BookingFindResource;
import com.ams.worknest.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingCreateResource bookingCreation(@RequestBody BookingCreateDto bookingCreateDto){
        return bookingService.createBooking(bookingCreateDto);
    }

    @GetMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.FOUND)
    public BookingFindResource bookingFindbyId(@PathVariable("bookingId") UUID bookingId){
        return bookingService.findBookingById(bookingId);
    }

}

package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.model.resources.BookingFindResource;
import com.ams.worknest.model.resources.BookingFindWorkStationResource;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingCreateResource createBooking(BookingCreateDto bookingCreateDto);

    BookingFindResource findBookingById(UUID bookingId);

    List<BookingFindWorkStationResource> findBookingsByDate(LocalDate date);
}

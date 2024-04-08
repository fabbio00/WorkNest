package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookingService {

    BookingCreateResource createBooking(BookingCreateDto bookingCreateDto);

    BookingFindResource findBookingById(UUID bookingId);

    List<BookingFindWorkStationResource> findBookingsByDate(LocalDate date);

    List<BookingFindByUserResource> findBookingsByUserId(UUID userId);

    BookingDeleteResource deleteBooking(UUID bookingId);


}

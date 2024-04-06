package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.model.resources.BookingFindResource;

import java.util.UUID;

public interface BookingService {

    BookingCreateResource createBooking(BookingCreateDto bookingCreateDto);

    BookingFindResource findBookingById(UUID bookingId);

}

package com.ams.worknest.services;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.BookingCreateResource;

public interface BookingService {

    BookingCreateResource createBooking(BookingCreateDto bookingCreateDto);

}

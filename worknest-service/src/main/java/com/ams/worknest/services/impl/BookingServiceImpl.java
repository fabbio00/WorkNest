package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public BookingCreateResource createBooking(BookingCreateDto bookingDto) {

        return null;
    }

}

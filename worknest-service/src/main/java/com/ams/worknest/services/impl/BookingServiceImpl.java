package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.model.resources.BookingFindResource;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final WorkStationRepository workStationRepository;

    @Override
    public BookingCreateResource createBooking(BookingCreateDto bookingDto) {

        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkStation workStation = workStationRepository.findById(bookingDto.getWorkstationId())
                .orElseThrow(() -> new RuntimeException("Workstation not found"));

        Booking booking = Booking.builder()
                .user(user)
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .status(bookingDto.getStatus())
                .hasPenalty(bookingDto.isHasPenalty())
                .workStation(workStation)
                .build();

        Booking bookingSaved = bookingRepository.save(booking);

        return BookingCreateResource.builder()
                .user(bookingSaved.getUser())
                .startDate(bookingSaved.getStartDate())
                .endDate(bookingSaved.getEndDate())
                .workStation(workStation)
                .build();

    }

    @Override
    public BookingFindResource findBookingById(UUID bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return BookingFindResource.builder()
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .endDate(booking.getEndDate())
                .hasPenalty(booking.isHasPenalty())
                .startDate(booking.getStartDate())
                .workStation(booking.getWorkStation())
                .status(booking.getStatus())
                .build();
    }

}

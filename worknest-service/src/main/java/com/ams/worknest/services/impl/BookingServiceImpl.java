package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.BookingCreateResource;
import com.ams.worknest.model.resources.BookingFindByUserResource;
import com.ams.worknest.model.resources.BookingFindResource;
import com.ams.worknest.model.resources.BookingFindWorkStationResource;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



/**
 * Implementation of the {@link BookingService} interface.
 * Provides methods for creating and retrieving booking details.
 */
@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final WorkStationRepository workStationRepository;

    /**
     * Creates a booking based on the provided booking data transfer object.
     *
     * @param bookingDto the booking data transfer object containing booking details
     * @return the created booking as a resource
     * @throws RuntimeException if the user or workstation associated with the booking is not found
     */
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

    /**
     * Retrieves a booking by its unique identifier.
     *
     * @param bookingId the UUID of the booking to retrieve
     * @return the requested booking as a resource
     * @throws RuntimeException if the booking with the given ID is not found
     */
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

    /**
     * Retrieves bookings for workstations based on the given date.
     *
     * @param date the date for which bookings are to be retrieved
     * @return a list of workstation bookings for the given date
     */
    @Override
    public List<BookingFindWorkStationResource> findBookingsByDate(LocalDate date) {

        List<Booking> bookings = bookingRepository.findByStartDateOnly(date);

        if (bookings.isEmpty()) {
            return Collections.emptyList();
        }

        return bookings.stream()
                .map(booking -> BookingFindWorkStationResource.builder()
                        .workStationId(booking.getWorkStation().getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingFindByUserResource> findBookingsByUserId(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        List<Booking> bookings = bookingRepository.findByUser(user);

        if (bookings.isEmpty()) {
            return Collections.emptyList();
        }

        return bookings.stream()
                .map(booking -> BookingFindByUserResource.builder()
                        .bookingId(booking.getId())
                        .startDate(booking.getStartDate())
                        .endDate(booking.getEndDate())
                        .checkIn(booking.getCheckIn())
                        .checkOut(booking.getCheckOut())
                        .status(booking.getStatus())
                        .workStationId(booking.getWorkStation().getId())
                        .build())
                .collect(Collectors.toList());
    }


}

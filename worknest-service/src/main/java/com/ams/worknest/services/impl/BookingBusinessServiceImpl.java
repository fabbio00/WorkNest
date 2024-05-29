package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.entities.BookingBusiness;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.BookingBusinessCreateResource;
import com.ams.worknest.model.resources.BookingBusinessFindResource;
import com.ams.worknest.repositories.BookingBusinessRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.BookingBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Implementation of the {@link BookingBusinessService} interface.
 * Provides methods for creating and retrieving booking business details.
 */
@Component
@RequiredArgsConstructor
public class BookingBusinessServiceImpl implements BookingBusinessService {

    private final BookingBusinessRepository bookingBusinessRepository;
    private final UserRepository userRepository;


    /**
     * Creates a booking business based on the provided booking business data transfer object.
     *
     * @param bookingBusinessCreateDto the booking business data transfer object containing booking business details
     * @return the created booking business as a resource
     * @throws RuntimeException if the user associated with the booking business is not found
     */
    @Override
    public BookingBusinessCreateResource createBookingBusiness(BookingBusinessCreateDto bookingBusinessCreateDto) {
        User user = userRepository.findById(bookingBusinessCreateDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BookingBusiness bookingBusiness = BookingBusiness.builder()
                .bookingDate(bookingBusinessCreateDto.getBookingDate())
                .user(user)
                .build();

        BookingBusiness bookingBusinessSaved = bookingBusinessRepository.save(bookingBusiness);

        return BookingBusinessCreateResource.builder()
                .bookingBusinessId(bookingBusinessSaved.getId())
                .build();
    }

    /**
     * Finds a booking business by its ID.
     *
     * @param bookingBusinessId the ID of the booking business to find
     * @return the booking business as a resource
     * @throws RuntimeException if the booking business is not found
     */
    @Override
    public BookingBusinessFindResource findBookingBusinessById(UUID bookingBusinessId) {
        BookingBusiness bookingBusiness = bookingBusinessRepository.findById(bookingBusinessId)
                .orElseThrow(() -> new RuntimeException("Booking Business not found"));

        return BookingBusinessFindResource.builder()
                .user(bookingBusiness.getUser())
                .bookingDate(bookingBusiness.getBookingDate())
                .build();
    }
}

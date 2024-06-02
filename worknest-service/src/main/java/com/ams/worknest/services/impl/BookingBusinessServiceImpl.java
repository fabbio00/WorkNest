package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.dto.BookingBusinessListDeleteDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.BookingBusiness;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.BookingBusinessCreateResource;
import com.ams.worknest.model.resources.BookingBusinessResource;
import com.ams.worknest.model.resources.BookingDeleteResource;
import com.ams.worknest.model.resources.BookingFindByCompanyResource;
import com.ams.worknest.model.resources.UserResource;

import com.ams.worknest.repositories.BookingBusinessRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.services.BookingBusinessService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link BookingBusinessService} interface.
 * Provides methods for retrieving booking details for business bookings.
 */
@Component
@RequiredArgsConstructor
public class BookingBusinessServiceImpl implements BookingBusinessService {

    private final BookingBusinessRepository bookingBusinessRepository;
    private final UserRepository userRepository;

    private static final String BUSINESS_BOOKING_NOT_FOUND = "Business booking not found!";
    private static final String BUSINESS_USER_NOT_FOUND = "User not found!";


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
     * Finds bookings by business booking id and optionally by workstation type.
     *
     * @param businessBookingId The business booking id.
     * @param type The type of workstation to filter bookings by (optional).
     * @return A list of bookings associated with the given business booking id and workstation type.
     * @throws EntityNotFoundException if the business booking id is not found.
     */
    @Override
    public List<BookingFindByCompanyResource> findBookingsByBusinessBookingIdAndType(UUID businessBookingId, String type) {
        if (!bookingBusinessRepository.existsById(businessBookingId)) {
            throw new EntityNotFoundException(BUSINESS_BOOKING_NOT_FOUND);
        }

        List<Booking> bookings = bookingBusinessRepository.findBookingsByBusinessBookingIdAndType(businessBookingId, type);

        return bookings.stream()
                .map(booking -> {
                    UserResource userResource = UserResource.builder()
                            .id(booking.getUser().getId())
                            .name(booking.getUser().getName())
                            .surname(booking.getUser().getSurname())
                            .email(booking.getUser().getEmail())
                            .taxCode(booking.getUser().getTaxCode())
                            .type(booking.getUser().getType())
                            .barrierFreeFlag(booking.getUser().isBarrierFreeFlag())
                            .registrationDate(booking.getUser().getRegistrationDate())
                            .status(booking.getUser().getStatus())
                            .username(booking.getUser().getUsername())
                            .build();

                    return BookingFindByCompanyResource.builder()
                            .bookingId(booking.getId())
                            .startDate(booking.getStartDate())
                            .endDate(booking.getEndDate())
                            .checkIn(booking.getCheckIn())
                            .checkOut(booking.getCheckOut())
                            .status(booking.getStatus())
                            .hasPenalty(booking.getHasPenalty())
                            .workStationName(booking.getWorkStation().getName())
                            .workStationType(booking.getWorkStation().getType())
                            .workstationCostPerHour(booking.getWorkStation().getPricePerH())
                            .buildingName(booking.getWorkStation().getBuilding().getName())
                            .floorName("Floor " + booking.getWorkStation().getFloor().getNumberOfFloor())
                            .userResource(userResource)
                            .build();
                })
                .toList();
    }

    /**
     * Cancels bookings by their IDs.
     *
     * @param bookingIds The list of booking IDs to cancel.
     * @return A list of {@link BookingDeleteResource} representing the cancellation status.
     */
    @Override
    public List<BookingDeleteResource> cancelBookingsByIds(BookingBusinessListDeleteDto bookingIds) {
        List<BookingDeleteResource> deleteResources = bookingIds.getBookingIds().stream()
                .map(id -> BookingDeleteResource.builder()
                        .bookingId(id)
                        .status("canceled")
                        .build())
                .toList();

        bookingBusinessRepository.cancelBookingsByIds(bookingIds.getBookingIds());

        return deleteResources;
    }

    /**
     * Finds a booking business by its ID.
     *
     * @param bookingBusinessId The ID of the booking business to find.
     * @return The booking business as a {@link BookingBusinessResource}.
     * @throws RuntimeException if the booking business is not found.
     */
    @Override
    public BookingBusinessResource findBookingBusinessById(UUID bookingBusinessId) {
        BookingBusiness bookingBusiness = bookingBusinessRepository.findById(bookingBusinessId)
                .orElseThrow(() -> new RuntimeException(BUSINESS_BOOKING_NOT_FOUND));

        return BookingBusinessResource.builder()
                .id(bookingBusiness.getId())
                .userId(bookingBusiness.getUser().getId())
                .bookingDate(bookingBusiness.getBookingDate())
                .build();
    }

    /**
     * Finds booking businesses by user ID and optionally filters by date range.
     *
     * @param userId The user ID to filter booking businesses by.
     * @param startDate The start date to filter booking businesses by (optional).
     * @param endDate The end date to filter booking businesses by (optional).
     * @return A list of booking businesses associated with the given user ID and date range.
     * @throws EntityNotFoundException if the user ID is not found.
     */
    @Override
    public List<BookingBusinessResource> findBookingBusinessesByUserId(
            UUID userId, LocalDate startDate, LocalDate endDate) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException(BUSINESS_USER_NOT_FOUND);
        }

        List<BookingBusiness> bookingBusinesses = bookingBusinessRepository.findBookingBusinessesByUserId(userId);

        return bookingBusinesses.stream()
                .filter(booking -> (startDate == null || !booking.getBookingDate().toLocalDate().isBefore(startDate)) &&
                        (endDate == null || !booking.getBookingDate().toLocalDate().isAfter(endDate)))
                .map(bookingBusiness -> BookingBusinessResource.builder()
                        .id(bookingBusiness.getId())
                        .userId(bookingBusiness.getUser().getId())
                        .bookingDate(bookingBusiness.getBookingDate())
                        .build())
                .toList();
    }
}

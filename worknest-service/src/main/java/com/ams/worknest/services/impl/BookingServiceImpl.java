package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.dto.BookingEditDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.BookingBusiness;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.*;
import com.ams.worknest.repositories.BookingBusinessRepository;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.CompanyRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link BookingService} interface.
 * Provides methods for creating, retrieving, editing, and deleting booking details.
 */
@Component
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final WorkStationRepository workStationRepository;
    private final BookingBusinessRepository bookingBusinessRepository;
    private final CompanyRepository companyRepository;
    private static final String BOOKING_NOT_FOUND = "Booking not found!";
    private static final String WORKSTATION_NOT_FOUND = "Workstation not found!";
    private static final String COMPANY_NOT_FOUND = "Company not found!";

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

        WorkStation workStation = workStationRepository.findById(bookingDto.getWorkStationId())
                .orElseThrow(() -> new RuntimeException(WORKSTATION_NOT_FOUND));

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
                .orElseThrow(() -> new RuntimeException(BOOKING_NOT_FOUND));

        return BookingFindResource.builder()
                .checkIn(booking.getCheckIn())
                .checkOut(booking.getCheckOut())
                .endDate(booking.getEndDate())
                .hasPenalty(booking.getHasPenalty())
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
                        .status(booking.getStatus())
                        .user(booking.getUser())
                        .build())
                .toList();

    }

    /**
     * Retrieve bookings associated with a specific user.
     *
     * @param userId The UUID of the user to retrieve bookings for
     * @return A list of {@link BookingFindByUserResource} representing bookings made by the user
     * @throws RuntimeException if the user doesn't exist
     */
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
                .toList();
    }

    /**
     * Deletes a booking with the specified ID and returns the details of the deleted booking.
     *
     * @param bookingId The UUID of the booking to be deleted.
     * @return The resource containing the ID of the deleted booking and its status after deletion.
     * @throws RuntimeException if the booking with the specified ID is not found.
     */
    @Override
    public BookingDeleteResource deleteBooking(UUID bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException(BOOKING_NOT_FOUND));

        booking.setStatus("canceled");

        Booking bookingDeleted = bookingRepository.save(booking);

        return BookingDeleteResource.builder()
                .bookingId(bookingId)
                .status(bookingDeleted.getStatus())
                .build();
    }

    /**
     * Edits the details of the booking with the specified ID using the provided data.
     *
     * @param bookingId      The UUID of the booking to be edited.
     * @param bookingEditDto The data containing the updated details for the booking.
     * @return The resource containing the updated details of the booking.
     * @throws RuntimeException if the booking with the specified ID is not found, or if the corresponding workstation is not found.
     */
    @Override
    public BookingEditResource editBooking(UUID bookingId, BookingEditDto bookingEditDto) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException(BOOKING_NOT_FOUND));

        WorkStation workStation = workStationRepository.findById(bookingEditDto.getWorkStationId())
                .orElseThrow(() -> new RuntimeException(WORKSTATION_NOT_FOUND));

        booking.setStartDate(bookingEditDto.getStartDate());
        booking.setEndDate(bookingEditDto.getEndDate());
        booking.setWorkStation(workStation);

        Booking bookingEdited = bookingRepository.save(booking);

        return BookingEditResource.builder()
                .startDate(bookingEdited.getStartDate())
                .endDate(bookingEdited.getEndDate())
                .workStation(workStation)
                .build();
    }

    /**
     * Saves a list of bookings based on the provided booking data transfer objects.
     *
     * @param bookingCreateDtos the list of booking data transfer objects containing booking details
     * @return the list of created bookings as resources
     * @throws RuntimeException if the user, workstation, or booking business associated with the booking is not found
     */
    @Override
    public List<BookingCreateResource> saveBookings(List<BookingCreateDto> bookingCreateDtos) {
        List<Booking> savedBookings = bookingRepository.saveAll(
                bookingCreateDtos.stream()
                        .map(bookingCreateDto -> {
                            // Fetch user and workstation
                            User user = userRepository.findById(bookingCreateDto.getUserId())
                                    .orElseThrow(() -> new RuntimeException("User not found"));
                            WorkStation workStation = workStationRepository.findById(bookingCreateDto.getWorkStationId())
                                    .orElseThrow(() -> new RuntimeException(WORKSTATION_NOT_FOUND));

                            // Conditionally fetch booking business
                            BookingBusiness bookingBusiness = null;
                            if (bookingCreateDto.getBookingBusinessId() != null) {
                                bookingBusiness = bookingBusinessRepository.findById(bookingCreateDto.getBookingBusinessId())
                                        .orElseThrow(() -> new RuntimeException("Booking Business not found"));
                            }

                            // Build and return Booking object
                            return Booking.builder()
                                    .user(user)
                                    .startDate(bookingCreateDto.getStartDate())
                                    .endDate(bookingCreateDto.getEndDate())
                                    .status(bookingCreateDto.getStatus())
                                    .hasPenalty(bookingCreateDto.isHasPenalty())
                                    .bookingBusiness(bookingBusiness)
                                    .workStation(workStation)
                                    .build();
                        })
                        .toList()
        );

        return savedBookings.stream()
                .map(savedBooking -> BookingCreateResource.builder()
                        .user(savedBooking.getUser())
                        .startDate(savedBooking.getStartDate())
                        .endDate(savedBooking.getEndDate())
                        .workStation(savedBooking.getWorkStation())
                        .build()
                )
                .toList();
    }


    /**
     * Retrieves bookings associated with a specific company and optionally filters them by employee name, surname, start date, and end date.
     *
     * @param companyId       The UUID of the company to retrieve bookings for.
     * @param employeeName    The name of the employee to filter bookings by (optional).
     * @param employeeSurname The surname of the employee to filter bookings by (optional).
     * @param startDate       The start date of the period to filter bookings by (optional).
     * @param endDate         The end date of the period to filter bookings by (optional).
     * @return A list of {@link BookingFindByCompanyResource} representing bookings associated with the company.
     * @throws EntityNotFoundException if the company doesn't exist.
     */
    @Override
    public List<BookingFindByCompanyResource> findBookingsByCompanyId(
            UUID companyId, String employeeName, String employeeSurname, LocalDate startDate, LocalDate endDate
    ) {
        if (!companyRepository.existsById(companyId)) {
            throw new EntityNotFoundException(COMPANY_NOT_FOUND);
        }

        List<User> users = userRepository.findByCompanyId(companyId);

        if (employeeName != null && !employeeName.isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getName().toLowerCase().contains(employeeName.toLowerCase()))
                    .toList();
        }
        if (employeeSurname != null && !employeeSurname.isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getSurname().toLowerCase().contains(employeeSurname.toLowerCase()))
                    .toList();
        }

        List<Booking> allBookings = new ArrayList<>();

        for (User user : users) {
            List<Booking> bookings = bookingRepository.findByUser(user);
            allBookings.addAll(bookings);
        }

        if (allBookings.isEmpty()) {
            return Collections.emptyList();
        }

        return allBookings.stream()
                .filter(booking -> (startDate == null || !booking.getStartDate().toLocalDate().isBefore(startDate)) &&
                        (endDate == null || !booking.getEndDate().toLocalDate().isAfter(endDate)))
                .map(booking -> {
                    User user = booking.getUser();
                    UserResource userResource = UserResource.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .email(user.getEmail())
                            .taxCode(user.getTaxCode())
                            .type(user.getType())
                            .barrierFreeFlag(user.isBarrierFreeFlag())
                            .registrationDate(user.getRegistrationDate())
                            .status(user.getStatus())
                            .username(user.getUsername())
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
                            .workstationCostPerHour(booking.getWorkStation().getPricePerH())
                            .buildingName(booking.getWorkStation().getBuilding().getName())
                            .floorName("Floor " + booking.getWorkStation().getFloor().getNumberOfFloor())
                            .userResource(userResource)
                            .build();
                })
                .toList();
    }
}
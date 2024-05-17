package com.ams.worknest.unit;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import com.ams.worknest.model.dto.BookingEditDto;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.BookingEditResource;
import com.ams.worknest.model.resources.BookingFindResource;
import com.ams.worknest.model.resources.BookingFindWorkStationResource;
import com.ams.worknest.model.resources.BookingDeleteResource;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.impl.BookingServiceImpl;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.BookingFindByUserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkStationRepository workStationRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test find booking by id")
    @Test
     void testFindBookingById() {
        // Arrange
        UUID bookingId = UUID.randomUUID(); // generate a random UUID
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus("active");
        WorkStation workStation = new WorkStation();
        workStation.setId(UUID.randomUUID());
        booking.setWorkStation(workStation);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        // Act
        BookingFindResource foundBooking = bookingService.findBookingById(bookingId);

        // Assert
        assertEquals(booking.getWorkStation().getId(), foundBooking.getWorkStation().getId());
        assertEquals(booking.getStatus(), foundBooking.getStatus());
        assertEquals(booking.getWorkStation().getId(), foundBooking.getWorkStation().getId());
        verify(bookingRepository, times(1)).findById(bookingId);
    }


    @DisplayName("Test find bookings by date")
    @Test
     void testFindBookingsByDate() {
        LocalDate date = LocalDate.now();
        List<Booking> bookings = new ArrayList<>();

        ZoneId zoneId = ZoneId.systemDefault();

        WorkStation workStation1 = new WorkStation();
        workStation1.setId(UUID.randomUUID());

        Booking booking1 = new Booking();
        booking1.setId(UUID.randomUUID());
        booking1.setStartDate(date.atStartOfDay(zoneId));
        booking1.setWorkStation(workStation1); // set WorkStation
        bookings.add(booking1);

        WorkStation workStation2 = new WorkStation();
        workStation2.setId(UUID.randomUUID());

        Booking booking2 = new Booking();
        booking2.setId(UUID.randomUUID());
        booking2.setStartDate(date.atStartOfDay(zoneId));
        booking2.setWorkStation(workStation2); // set WorkStation
        bookings.add(booking2);

        when(bookingRepository.findByStartDateOnly(date)).thenReturn(bookings);

        List<BookingFindWorkStationResource> foundBookings = bookingService.findBookingsByDate(date);

        assertEquals(2, foundBookings.size());
        assertEquals(workStation1.getId(), foundBookings.get(0).getWorkStationId());
        assertEquals(workStation2.getId(), foundBookings.get(1).getWorkStationId());
    }

    @DisplayName("Test find bookings by user id")
    @Test
     void testFindBookingsByUserId() {
        // Arrange
        UUID userId = UUID.randomUUID(); // generate a random UUID
        User user = new User();
        user.setId(userId);
        List<Booking> bookings = new ArrayList<>();

        WorkStation workStation1 = new WorkStation();
        workStation1.setId(UUID.randomUUID());

        Booking booking1 = new Booking();
        booking1.setId(UUID.randomUUID());
        booking1.setUser(user);
        booking1.setWorkStation(workStation1); // set WorkStation
        bookings.add(booking1);

        WorkStation workStation2 = new WorkStation();
        workStation2.setId(UUID.randomUUID());

        Booking booking2 = new Booking();
        booking2.setId(UUID.randomUUID());
        booking2.setUser(user);
        booking2.setWorkStation(workStation2); // set WorkStation
        bookings.add(booking2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookingRepository.findByUser(user)).thenReturn(bookings);

        // Act
        List<BookingFindByUserResource> foundBookings = bookingService.findBookingsByUserId(userId);

        // Assert
        assertEquals(2, foundBookings.size());
        assertEquals(booking1.getId(), foundBookings.get(0).getBookingId());
        assertEquals(booking2.getId(), foundBookings.get(1).getBookingId());
        assertEquals(workStation1.getId(), foundBookings.get(0).getWorkStationId());
        assertEquals(workStation2.getId(), foundBookings.get(1).getWorkStationId());
    }

    @DisplayName("Test edit booking")
    @Test
     void testEditBooking() {
        UUID bookingId = UUID.randomUUID(); // generate a random UUID
        UUID workStationId = UUID.randomUUID(); // generate a random UUID
        Booking booking = new Booking();
        booking.setId(bookingId);
        WorkStation workStation = new WorkStation();
        workStation.setId(workStationId);
        BookingEditDto bookingEditDto = new BookingEditDto();
        bookingEditDto.setWorkStationId(workStationId);
        bookingEditDto.setStartDate(ZonedDateTime.now());
        bookingEditDto.setEndDate(ZonedDateTime.now().plusDays(1));

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(workStationRepository.findById(workStationId)).thenReturn(Optional.of(workStation));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        BookingEditResource editedBooking = bookingService.editBooking(bookingId, bookingEditDto);

        assertEquals(bookingEditDto.getStartDate(), editedBooking.getStartDate());
        assertEquals(bookingEditDto.getEndDate(), editedBooking.getEndDate());
        assertEquals(workStationId, editedBooking.getWorkStation().getId());
    }

    @DisplayName("Test delete booking")
    @Test
     void testDeleteBooking() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus("active"); // set initial status

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        BookingDeleteResource deletedBooking = bookingService.deleteBooking(bookingId);

        // Add assertions to verify the deleted booking...
        assertEquals(bookingId, deletedBooking.getBookingId());
        assertEquals("canceled", deletedBooking.getStatus());

        // Verify that the save method was called
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @DisplayName("Test find booking by id with non-existing id")
    @Test
     void testFindBookingByIdNonExisting() {
        UUID bookingId = UUID.randomUUID(); // generate a random UUID

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingService.findBookingById(bookingId));
    }

    @DisplayName("Test find bookings by date with no bookings")
    @Test
     void testFindBookingsByDateNoBookings() {
        LocalDate date = LocalDate.now();

        when(bookingRepository.findByStartDateOnly(date)).thenReturn(new ArrayList<>());

        List<BookingFindWorkStationResource> foundBookings = bookingService.findBookingsByDate(date);

        assertEquals(0, foundBookings.size());
    }

    @DisplayName("Test find bookings by user id with non-existing user")
    @Test
     void testFindBookingsByUserIdNonExisting() {
        UUID userId = UUID.randomUUID(); // generate a random UUID

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingService.findBookingsByUserId(userId));
    }

    @DisplayName("Test edit booking with non-existing booking")
    @Test
     void testEditBookingNonExisting() {
        UUID bookingId = UUID.randomUUID(); // generate a random UUID
        UUID workStationId = UUID.randomUUID(); // generate a random UUID
        BookingEditDto bookingEditDto = new BookingEditDto();
        bookingEditDto.setWorkStationId(workStationId);
        bookingEditDto.setStartDate(ZonedDateTime.now());
        bookingEditDto.setEndDate(ZonedDateTime.now().plusDays(1));

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingService.editBooking(bookingId, bookingEditDto));
    }

    @DisplayName("Test delete booking with non-existing booking")
    @Test
     void testDeleteBookingNonExisting() {
        UUID bookingId = UUID.randomUUID();

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingService.deleteBooking(bookingId));
    }
}
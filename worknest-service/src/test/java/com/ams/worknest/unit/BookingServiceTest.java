package com.ams.worknest.unit;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.dto.BookingEditDto;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.*;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.CompanyRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.impl.BookingServiceImpl;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CompanyRepository companyRepository;

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
        
        UUID bookingId = UUID.randomUUID(); 
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus("active");
        booking.setHasPenalty(false);
        WorkStation workStation = new WorkStation();
        workStation.setId(UUID.randomUUID());
        booking.setWorkStation(workStation);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        BookingFindResource foundBooking = bookingService.findBookingById(bookingId);

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
        booking1.setWorkStation(workStation1);
        bookings.add(booking1);

        WorkStation workStation2 = new WorkStation();
        workStation2.setId(UUID.randomUUID());

        Booking booking2 = new Booking();
        booking2.setId(UUID.randomUUID());
        booking2.setStartDate(date.atStartOfDay(zoneId));
        booking2.setWorkStation(workStation2);
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
        
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        List<Booking> bookings = new ArrayList<>();

        WorkStation workStation1 = new WorkStation();
        workStation1.setId(UUID.randomUUID());

        Booking booking1 = new Booking();
        booking1.setId(UUID.randomUUID());
        booking1.setUser(user);
        booking1.setWorkStation(workStation1);
        bookings.add(booking1);

        WorkStation workStation2 = new WorkStation();
        workStation2.setId(UUID.randomUUID());

        Booking booking2 = new Booking();
        booking2.setId(UUID.randomUUID());
        booking2.setUser(user);
        booking2.setWorkStation(workStation2);
        bookings.add(booking2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookingRepository.findByUser(user)).thenReturn(bookings);
        
        List<BookingFindByUserResource> foundBookings = bookingService.findBookingsByUserId(userId);
        
        assertEquals(2, foundBookings.size());
        assertEquals(booking1.getId(), foundBookings.get(0).getBookingId());
        assertEquals(booking2.getId(), foundBookings.get(1).getBookingId());
        assertEquals(workStation1.getId(), foundBookings.get(0).getWorkStationId());
        assertEquals(workStation2.getId(), foundBookings.get(1).getWorkStationId());
    }

    @DisplayName("Test edit booking")
    @Test
     void testEditBooking() {
        UUID bookingId = UUID.randomUUID(); 
        UUID workStationId = UUID.randomUUID(); 
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
        booking.setStatus("active"); 

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        BookingDeleteResource deletedBooking = bookingService.deleteBooking(bookingId);

        assertEquals(bookingId, deletedBooking.getBookingId());
        assertEquals("canceled", deletedBooking.getStatus());

        verify(bookingRepository, times(1)).save(any(Booking.class));
    }

    @DisplayName("Test find booking by id with non-existing id")
    @Test
     void testFindBookingByIdNonExisting() {
        UUID bookingId = UUID.randomUUID(); 

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
        UUID userId = UUID.randomUUID(); 

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingService.findBookingsByUserId(userId));
    }

    @DisplayName("Test edit booking with non-existing booking")
    @Test
     void testEditBookingNonExisting() {
        UUID bookingId = UUID.randomUUID(); 
        UUID workStationId = UUID.randomUUID(); 
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

    @DisplayName("Test find bookings by user id with no bookings")
    @Test
    void testFindBookingsByUserIdNoBookings() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookingRepository.findByUser(user)).thenReturn(new ArrayList<>());

        List<BookingFindByUserResource> foundBookings = bookingService.findBookingsByUserId(userId);

        assertEquals(0, foundBookings.size());
    }

    @DisplayName("Test edit booking with non-existing workstation")
    @Test
    void testEditBookingNonExistingWorkStation() {
        UUID bookingId = UUID.randomUUID();
        UUID workStationId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);
        BookingEditDto bookingEditDto = new BookingEditDto();
        bookingEditDto.setWorkStationId(workStationId);
        bookingEditDto.setStartDate(ZonedDateTime.now());
        bookingEditDto.setEndDate(ZonedDateTime.now().plusDays(1));

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(workStationRepository.findById(workStationId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingService.editBooking(bookingId, bookingEditDto));
    }

    @DisplayName("Test delete booking with already canceled booking")
    @Test
    void testDeleteBookingAlreadyCanceled() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus("canceled"); 

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        assertThrows(RuntimeException.class, () -> bookingService.deleteBooking(bookingId));
    }

    @DisplayName("Test find bookings by user id with one booking")
    @Test
    void testFindBookingsByUserIdOneBooking() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        List<Booking> bookings = new ArrayList<>();

        WorkStation workStation = new WorkStation();
        workStation.setId(UUID.randomUUID());

        Booking booking = new Booking();
        booking.setId(UUID.randomUUID());
        booking.setUser(user);
        booking.setWorkStation(workStation);
        bookings.add(booking);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookingRepository.findByUser(user)).thenReturn(bookings);

        List<BookingFindByUserResource> foundBookings = bookingService.findBookingsByUserId(userId);

        assertEquals(1, foundBookings.size());
        assertEquals(booking.getId(), foundBookings.get(0).getBookingId());
        assertEquals(workStation.getId(), foundBookings.get(0).getWorkStationId());
    }

    @DisplayName("Test edit booking with already canceled booking")
    @Test
    void testEditBookingAlreadyCanceled() {
        UUID bookingId = UUID.randomUUID();
        UUID workStationId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus("canceled");
        BookingEditDto bookingEditDto = new BookingEditDto();
        bookingEditDto.setWorkStationId(workStationId);
        bookingEditDto.setStartDate(ZonedDateTime.now());
        bookingEditDto.setEndDate(ZonedDateTime.now().plusDays(1));

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        assertThrows(RuntimeException.class, () -> bookingService.editBooking(bookingId, bookingEditDto));
    }

    @DisplayName("Test delete booking with already deleted booking")
    @Test
    void testDeleteBookingAlreadyDeleted() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus("canceled");

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        assertThrows(RuntimeException.class, () -> bookingService.deleteBooking(bookingId));
    }

    @DisplayName("Test create booking with valid data")
    @Test
    void testCreateBookingWithValidData() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        UUID workStationId = UUID.randomUUID();
        WorkStation workStation = new WorkStation();
        workStation.setId(workStationId);

        BookingCreateDto bookingCreateDto = new BookingCreateDto();
        bookingCreateDto.setUserId(userId); // Assuming BookingCreateDto has a userId field
        bookingCreateDto.setWorkStationId(workStationId);
        bookingCreateDto.setStartDate(ZonedDateTime.now());
        bookingCreateDto.setEndDate(ZonedDateTime.now().plusDays(1));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user)); // Mock the UserRepository
        when(workStationRepository.findById(workStationId)).thenReturn(Optional.of(workStation)); // Mock the WorkStationRepository
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);

        BookingCreateResource createdBooking = bookingService.createBooking(bookingCreateDto);

        assertEquals(bookingCreateDto.getStartDate(), createdBooking.getStartDate());
        assertEquals(bookingCreateDto.getEndDate(), createdBooking.getEndDate());
        assertEquals(workStationId, createdBooking.getWorkStation().getId()); // Verify the WorkStation
    }

    @DisplayName("Test create booking with invalid data")
    @Test
    void testCreateBookingWithInvalidData() {
        BookingCreateDto bookingCreateDto = new BookingCreateDto();

        assertThrows(RuntimeException.class, () -> bookingService.createBooking(bookingCreateDto));
    }

    @DisplayName("Test find bookings by company id with valid company id")
    @Test
    void testFindBookingsByCompanyIdWithValidCompanyId() {
        UUID companyId = UUID.randomUUID();
        String employeeName = "John";
        String employeeSurname = "Doe";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        when(companyRepository.existsById(companyId)).thenReturn(true); // Mock the CompanyRepository

        List<BookingFindByCompanyResource> foundBookings = bookingService.findBookingsByCompanyId(companyId, employeeName, employeeSurname, startDate, endDate);

        assertNotNull(foundBookings);
    }

    @DisplayName("Test find bookings by company id with non-existing company id")
    @Test
    void testFindBookingsByCompanyIdWithNonExistingCompanyId() {
        UUID companyId = UUID.randomUUID();
        String employeeName = "John";
        String employeeSurname = "Doe";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        assertThrows(RuntimeException.class, () -> bookingService.findBookingsByCompanyId(companyId, employeeName, employeeSurname, startDate, endDate));
    }


}
package com.ams.worknest.unit;

import com.ams.worknest.model.dto.BookingBusinessListDeleteDto;
import com.ams.worknest.model.entities.*;
import com.ams.worknest.model.resources.BookingDeleteResource;
import com.ams.worknest.model.resources.BookingFindByCompanyResource;
import com.ams.worknest.repositories.BookingBusinessRepository;
import com.ams.worknest.services.impl.BookingBusinessServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingBusinessServiceTest {

    @InjectMocks
    private BookingBusinessServiceImpl bookingBusinessService;

    @Mock
    private BookingBusinessRepository bookingBusinessRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test find bookings by business booking id and type")
    @Test
    void testFindBookingsByBusinessBookingIdAndType() {
        UUID businessBookingId = UUID.randomUUID();
        String type = "desk";

        User user = new User();
        user.setId(UUID.randomUUID());

        Building building = new Building();
        building.setName("Building1");

        Floor floor = new Floor();
        floor.setNumberOfFloor(2);

        WorkStation workStation = new WorkStation();
        workStation.setType(type);
        workStation.setBuilding(building);
        workStation.setFloor(floor);

        BookingBusiness bookingBusiness = new BookingBusiness();
        bookingBusiness.setId(businessBookingId);
        bookingBusiness.setUser(user);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setWorkStation(workStation);
        booking.setBookingBusiness(bookingBusiness);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        when(bookingBusinessRepository.existsById(businessBookingId)).thenReturn(true);
        when(bookingBusinessRepository.findBookingsByBusinessBookingIdAndType(businessBookingId, type)).thenReturn(bookings);

        List<BookingFindByCompanyResource> foundBookings = bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);

        assertEquals(1, foundBookings.size());
        assertEquals(user.getId(), foundBookings.get(0).getUserResource().getId());
        assertEquals(type, foundBookings.get(0).getWorkStationType());
        verify(bookingBusinessRepository, times(1)).existsById(businessBookingId);
        verify(bookingBusinessRepository, times(1)).findBookingsByBusinessBookingIdAndType(businessBookingId, type);
    }


    @DisplayName("Test cancel bookings by ids")
    @Test
    void testCancelBookingsByIds() {
        UUID bookingId1 = UUID.randomUUID();
        UUID bookingId2 = UUID.randomUUID();

        BookingBusinessListDeleteDto bookingBusinessListDeleteDto = new BookingBusinessListDeleteDto();
        bookingBusinessListDeleteDto.setBookingIds(List.of(bookingId1, bookingId2));

        List<UUID> bookingIds = bookingBusinessListDeleteDto.getBookingIds();

        doNothing().when(bookingBusinessRepository).cancelBookingsByIds(bookingIds);

        List<BookingDeleteResource> canceledBookings = bookingBusinessService.cancelBookingsByIds(bookingBusinessListDeleteDto);

        assertEquals(2, canceledBookings.size());
        assertEquals("canceled", canceledBookings.get(0).getStatus());
        assertEquals(bookingId1, canceledBookings.get(0).getBookingId());
        assertEquals("canceled", canceledBookings.get(1).getStatus());
        assertEquals(bookingId2, canceledBookings.get(1).getBookingId());
        verify(bookingBusinessRepository, times(1)).cancelBookingsByIds(bookingIds);
    }

    @DisplayName("Test find bookings by business booking id and type with no bookings")
    @Test
    void testFindBookingsByBusinessBookingIdAndTypeNoBookings() {
        UUID businessBookingId = UUID.randomUUID();
        String type = "desk";

        when(bookingBusinessRepository.existsById(businessBookingId)).thenReturn(true);
        when(bookingBusinessRepository.findBookingsByBusinessBookingIdAndType(businessBookingId, type)).thenReturn(new ArrayList<>());

        List<BookingFindByCompanyResource> foundBookings = bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);

        assertTrue(foundBookings.isEmpty());
        verify(bookingBusinessRepository, times(1)).existsById(businessBookingId);
        verify(bookingBusinessRepository, times(1)).findBookingsByBusinessBookingIdAndType(businessBookingId, type);
    }

    @DisplayName("Test find bookings by business booking id and type with multiple bookings")
    @Test
    void testFindBookingsByBusinessBookingIdAndTypeMultipleBookings() {
        UUID businessBookingId = UUID.randomUUID();
        String type = "desk";

        User user = new User();
        user.setId(UUID.randomUUID());

        Building building = new Building();
        building.setName("Building1");

        Floor floor = new Floor();
        floor.setNumberOfFloor(2);

        WorkStation workStation1 = new WorkStation();
        workStation1.setType(type);
        workStation1.setBuilding(building);
        workStation1.setFloor(floor);

        WorkStation workStation2 = new WorkStation();
        workStation2.setType(type);
        workStation2.setBuilding(building);
        workStation2.setFloor(floor);

        BookingBusiness bookingBusiness = new BookingBusiness();
        bookingBusiness.setId(businessBookingId);
        bookingBusiness.setUser(user);

        Booking booking1 = new Booking();
        booking1.setUser(user);
        booking1.setWorkStation(workStation1);
        booking1.setBookingBusiness(bookingBusiness);

        Booking booking2 = new Booking();
        booking2.setUser(user);
        booking2.setWorkStation(workStation2);
        booking2.setBookingBusiness(bookingBusiness);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);

        when(bookingBusinessRepository.existsById(businessBookingId)).thenReturn(true);
        when(bookingBusinessRepository.findBookingsByBusinessBookingIdAndType(businessBookingId, type)).thenReturn(bookings);

        List<BookingFindByCompanyResource> foundBookings = bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);

        assertEquals(2, foundBookings.size());
        assertEquals(user.getId(), foundBookings.get(0).getUserResource().getId());
        assertEquals(type, foundBookings.get(0).getWorkStationType());
        assertEquals(user.getId(), foundBookings.get(1).getUserResource().getId());
        assertEquals(type, foundBookings.get(1).getWorkStationType());
        verify(bookingBusinessRepository, times(1)).existsById(businessBookingId);
        verify(bookingBusinessRepository, times(1)).findBookingsByBusinessBookingIdAndType(businessBookingId, type);
    }


    @DisplayName("Test cancel bookings by ids with empty list")
    @Test
    void testCancelBookingsByIdsEmptyList() {
        BookingBusinessListDeleteDto bookingBusinessListDeleteDto = new BookingBusinessListDeleteDto();
        bookingBusinessListDeleteDto.setBookingIds(new ArrayList<>());

        List<BookingDeleteResource> canceledBookings = bookingBusinessService.cancelBookingsByIds(bookingBusinessListDeleteDto);

        assertTrue(canceledBookings.isEmpty());
        verify(bookingBusinessRepository, times(1)).cancelBookingsByIds(new ArrayList<>());
    }

    @DisplayName("Test find bookings by business booking id and type with non-existing type")
    @Test
    void testFindBookingsByBusinessBookingIdAndTypeNonExistingType() {
        UUID businessBookingId = UUID.randomUUID();
        String type = "nonexistent_type";

        User user = new User();
        user.setId(UUID.randomUUID());

        Building building = new Building();
        building.setName("Building1");

        Floor floor = new Floor();
        floor.setNumberOfFloor(2);

        WorkStation workStation = new WorkStation();
        workStation.setType(type);
        workStation.setBuilding(building);
        workStation.setFloor(floor);

        BookingBusiness bookingBusiness = new BookingBusiness();
        bookingBusiness.setId(businessBookingId);
        bookingBusiness.setUser(user);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setWorkStation(workStation);
        booking.setBookingBusiness(bookingBusiness);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);

        when(bookingBusinessRepository.existsById(businessBookingId)).thenReturn(true);
        when(bookingBusinessRepository.findBookingsByBusinessBookingIdAndType(businessBookingId, type)).thenReturn(new ArrayList<>());

        List<BookingFindByCompanyResource> foundBookings = bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);

        assertTrue(foundBookings.isEmpty());
        verify(bookingBusinessRepository, times(1)).existsById(businessBookingId);
        verify(bookingBusinessRepository, times(1)).findBookingsByBusinessBookingIdAndType(businessBookingId, type);
    }

    @DisplayName("Test cancel bookings by ids with one non-existing booking id")
    @Test
    void testCancelBookingsByIdsOneNonExistingBookingId() {
        UUID bookingId1 = UUID.randomUUID();
        UUID bookingId2 = UUID.randomUUID();

        BookingBusinessListDeleteDto bookingBusinessListDeleteDto = new BookingBusinessListDeleteDto();
        bookingBusinessListDeleteDto.setBookingIds(List.of(bookingId1, bookingId2));

        List<UUID> bookingIds = bookingBusinessListDeleteDto.getBookingIds();

        doNothing().when(bookingBusinessRepository).cancelBookingsByIds(bookingIds);

        List<BookingDeleteResource> canceledBookings = bookingBusinessService.cancelBookingsByIds(bookingBusinessListDeleteDto);

        assertEquals(2, canceledBookings.size());
        assertEquals("canceled", canceledBookings.get(0).getStatus());
        assertEquals(bookingId1, canceledBookings.get(0).getBookingId());
        assertEquals("canceled", canceledBookings.get(1).getStatus());
        assertEquals(bookingId2, canceledBookings.get(1).getBookingId());
        verify(bookingBusinessRepository, times(1)).cancelBookingsByIds(bookingIds);
    }

    @DisplayName("Test find bookings by business booking id and type with non-existing business booking id")
    @Test
    void testFindBookingsByBusinessBookingIdAndTypeNonExistingBusinessBookingId() {
        UUID businessBookingId = UUID.randomUUID();
        String type = "desk";

        when(bookingBusinessRepository.existsById(businessBookingId)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);
        });

        String expectedMessage = "Business booking not found!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(bookingBusinessRepository, times(1)).existsById(businessBookingId);
        verify(bookingBusinessRepository, times(0)).findBookingsByBusinessBookingIdAndType(any(UUID.class), anyString());
    }

    @DisplayName("Test find bookings by business booking id and type desk")
    @Test
    void testFindBookingsByBusinessBookingIdAndTypeDesk() {
        UUID businessBookingId = UUID.randomUUID();
        String type = "desk";

        User user = new User();
        user.setId(UUID.randomUUID());

        Building building = new Building();
        building.setName("Building1");

        Floor floor = new Floor();
        floor.setNumberOfFloor(2);

        WorkStation workStation1 = new WorkStation();
        workStation1.setType(type);
        workStation1.setBuilding(building);
        workStation1.setFloor(floor);

        BookingBusiness bookingBusiness = new BookingBusiness();
        bookingBusiness.setId(businessBookingId);
        bookingBusiness.setUser(user);

        Booking booking1 = new Booking();
        booking1.setUser(user);
        booking1.setWorkStation(workStation1);
        booking1.setBookingBusiness(bookingBusiness);

        List<Booking> bookingsType1 = List.of(booking1);

        when(bookingBusinessRepository.existsById(businessBookingId)).thenReturn(true);
        when(bookingBusinessRepository.findBookingsByBusinessBookingIdAndType(businessBookingId, type)).thenReturn(bookingsType1);

        List<BookingFindByCompanyResource> foundBookingsType1 = bookingBusinessService.findBookingsByBusinessBookingIdAndType(businessBookingId, type);

        assertEquals(1, foundBookingsType1.size());
        assertEquals(user.getId(), foundBookingsType1.get(0).getUserResource().getId());
        assertEquals(type, foundBookingsType1.get(0).getWorkStationType());

        verify(bookingBusinessRepository, times(1)).existsById(businessBookingId);
        verify(bookingBusinessRepository, times(1)).findBookingsByBusinessBookingIdAndType(businessBookingId, type);
    }
}
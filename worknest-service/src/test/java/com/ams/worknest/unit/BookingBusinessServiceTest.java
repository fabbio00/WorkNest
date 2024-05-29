package com.ams.worknest.unit;

import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.entities.BookingBusiness;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.resources.BookingBusinessCreateResource;
import com.ams.worknest.model.resources.BookingBusinessFindResource;
import com.ams.worknest.repositories.BookingBusinessRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.impl.BookingBusinessServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingBusinessServiceTest {

    @InjectMocks
    private BookingBusinessServiceImpl bookingBusinessService;

    @Mock
    private BookingBusinessRepository bookingBusinessRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkStationRepository workStationRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create booking business successfully")
    @Test
    void createBookingBusinessSuccess() {
        BookingBusinessCreateDto dto = new BookingBusinessCreateDto();
        dto.setBookingDate(ZonedDateTime.now());
        dto.setUserId(UUID.randomUUID());

        User user = new User();
        user.setId(dto.getUserId());

        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
        when(bookingBusinessRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        BookingBusinessCreateResource result = bookingBusinessService.createBookingBusiness(dto);

        assertNotNull(result);
        verify(bookingBusinessRepository, times(1)).save(any());
    }

    @DisplayName("Create booking business with null DTO")
    @Test
    void createBookingBusinessNullDto() {
        assertThrows(RuntimeException.class, () -> bookingBusinessService.createBookingBusiness(null));
    }

    @DisplayName("Create booking business with null booking date")
    @Test
    void createBookingBusinessNullBookingDate() {
        BookingBusinessCreateDto dto = new BookingBusinessCreateDto();
        dto.setUserId(UUID.randomUUID());

        assertThrows(RuntimeException.class, () -> bookingBusinessService.createBookingBusiness(dto));
    }

    @DisplayName("Create booking business with null user id")
    @Test
    void createBookingBusinessNullUserId() {
        BookingBusinessCreateDto dto = new BookingBusinessCreateDto();
        dto.setBookingDate(ZonedDateTime.now());

        assertThrows(RuntimeException.class, () -> bookingBusinessService.createBookingBusiness(dto));
    }

    @DisplayName("Find booking business by id successfully")
    @Test
    void findBookingBusinessByIdSuccess() {
        UUID bookingBusinessId = UUID.randomUUID();

        when(bookingBusinessRepository.findById(bookingBusinessId)).thenReturn(Optional.of(new BookingBusiness()));

        BookingBusinessFindResource result = bookingBusinessService.findBookingBusinessById(bookingBusinessId);

        assertNotNull(result);
        verify(bookingBusinessRepository, times(1)).findById(bookingBusinessId);
    }

    @DisplayName("Find booking business by id with non-existing id")
    @Test
    void findBookingBusinessByIdNonExistingId() {
        UUID bookingBusinessId = UUID.randomUUID();

        when(bookingBusinessRepository.findById(bookingBusinessId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookingBusinessService.findBookingBusinessById(bookingBusinessId));
    }

    @DisplayName("Find booking business by id with null id")
    @Test
    void findBookingBusinessByIdNullId() {
        assertThrows(RuntimeException.class, () -> bookingBusinessService.findBookingBusinessById(null));
    }
}

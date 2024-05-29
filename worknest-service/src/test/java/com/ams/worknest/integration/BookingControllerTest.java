package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@ContextConfiguration(classes = BookingControllerTest.class)
class BookingControllerTest extends BaseMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkStationRepository workStationRepository;

    private static final String BOOKING_ENDPOINT = "/bookings";

    @Test
    void getBookingById() throws Exception{
        Booking savedBooking = savedBookingTemplate();
        mvc.perform(
                get(BOOKING_ENDPOINT + "/{bookingId}", savedBooking.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is(savedBooking.getStatus())))
                .andReturn();

    }


    @Test
    void getBookingsByDate() throws Exception {
        savedBookingTemplate();
        LocalDate date = LocalDate.of(2024, 4, 6);

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/findDesks")
                                .param("date", date.format(DateTimeFormatter.ISO_DATE))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getBookingsByUserId() throws Exception {

        Booking savedBooking = savedBookingTemplate();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list/{userId}", savedBooking.getUser().getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void deleteBooking() throws Exception {

        Booking savedBooking = savedBookingTemplate();

        mvc.perform(
                        put(BOOKING_ENDPOINT + "/delete/{bookingId}", savedBooking.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("canceled")));

    }

    @Test
    void saveBooking() throws Exception {
        BookingCreateDto bookingCreateDto = bookingDtoCreation2();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String bookingCreateDtosJson = mapper.writeValueAsString(bookingCreateDto);

        mvc.perform(
                        post(BOOKING_ENDPOINT)
                                .content(bookingCreateDtosJson)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void saveMultipleBookings() throws Exception {
        List<BookingCreateDto> bookingCreateDtos = Arrays.asList(bookingDtoCreation2(), bookingDtoCreation2());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String bookingCreateDtosJson = mapper.writeValueAsString(bookingCreateDtos);

        mvc.perform(
                        post(BOOKING_ENDPOINT + "/save-list")
                                .content(bookingCreateDtosJson)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }




    Booking savedBookingTemplate(){

        String str = "2024-04-06T10:15:30+01:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(str, formatter);

        User user = User.builder()
                .barrierFreeFlag(true)
                .email("prova.user@gmail.com")
                .password("password")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .build();

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3)
                .equipment("monitor")
                .type("desk")
                .build();

        Booking booking = Booking.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime)
                .checkIn(null)
                .checkOut(null)
                .status("active")
                .hasPenalty(false)
                .user(user)
                .workStation(workStation)
                .build();

        return bookingRepository.save(booking);
    }

    BookingCreateDto bookingDtoCreation(){

        String str = "2024-04-06T10:15:30+01:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(str, formatter);


        User user = User.builder()
                .barrierFreeFlag(true)
                .email("prova.user@gmail.com")
                .password("password")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .build();

        User savedUser = userRepository.save(user);

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3)
                .equipment("monitor")
                .type("desk")
                .build();

        WorkStation savedWorkStation = workStationRepository.save(workStation);

        return BookingCreateDto.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime)
                .status("active")
                .hasPenalty(false)
                .userId(savedUser.getId())
                .workStationId(savedWorkStation.getId())
                .build();
    }

    BookingCreateDto bookingDtoCreation2(){

        String str = "2024-04-06T10:15:30+01:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(str, formatter);

        User user = User.builder()
                .barrierFreeFlag(true)
                .email("prova.user@gmail.com")
                .password("password")
                .name("Mario")
                .surname("Rossi")
                .username("username")
                .type("basic_user")
                .taxCode("FDSAFDAR343")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .build();

        User savedUser = userRepository.save(user);

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3)
                .equipment("monitor")
                .type("desk")
                .build();

        WorkStation savedWorkStation = workStationRepository.save(workStation);

        return BookingCreateDto.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime)
                .status("active")
                .hasPenalty(false)
                .userId(savedUser.getId())
                .workStationId(savedWorkStation.getId())
                .bookingBusinessId(null)
                .build();
    }




}

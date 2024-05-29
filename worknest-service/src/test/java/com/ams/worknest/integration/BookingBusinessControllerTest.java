package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.entities.BookingBusiness;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.repositories.BookingBusinessRepository;
import com.ams.worknest.repositories.UserRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@ContextConfiguration(classes = BookingBusinessControllerTest.class)
class BookingBusinessControllerTest extends BaseMvcTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookingBusinessRepository bookingBusinessRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String BOOKING_BUSINESS_ENDPOINT = "/booking-business";

    @Test
    void getBookingBusinessById() throws Exception{
        BookingBusiness savedBookingBusiness = savedBookingBusiness();
        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/{bookingBusinessId}", savedBookingBusiness.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void saveBusinessBooking() throws Exception {
        BookingBusinessCreateDto bookingBusinessCreateDto = bookingBusinessDtoCreation();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String bookingCreateDtosJson = mapper.writeValueAsString(bookingBusinessCreateDto);

        mvc.perform(
                        post(BOOKING_BUSINESS_ENDPOINT)
                                .content(bookingCreateDtosJson)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }



    BookingBusiness savedBookingBusiness() {
            User user = User.builder()
                    .barrierFreeFlag(true)
                    .email("prova22.user@gmail.com")
                    .password("password")
                    .name("Mario")
                    .surname("Rossi")
                    .username("username")
                    .type("basic_user")
                    .taxCode("FDSAFDAR343")
                    .registrationDate(ZonedDateTime.now())
                    .status("active")
                    .build();
        userRepository.save(user);

        BookingBusiness savedBookingBusiness = BookingBusiness.builder()
                .bookingDate(ZonedDateTime.now())
                .user(user)
                .build();
        return bookingBusinessRepository.save(savedBookingBusiness);
    }

    BookingBusinessCreateDto bookingBusinessDtoCreation() {
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
        userRepository.save(user);

        return BookingBusinessCreateDto.builder()
                .bookingDate(ZonedDateTime.now())
                .userId(user.getId())
                .build();
    }

    @AfterEach
    void cleanUp() throws Exception {
        userRepository.deleteAll();
    }

}

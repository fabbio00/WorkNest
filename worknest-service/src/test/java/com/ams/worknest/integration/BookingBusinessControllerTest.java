package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.dto.BookingBusinessCreateDto;
import com.ams.worknest.model.dto.BookingBusinessListDeleteDto;
import com.ams.worknest.model.entities.*;
import com.ams.worknest.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = BookingBusinessControllerTest.class)
@Transactional
class BookingBusinessControllerTest extends BaseMvcTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkStationRepository workStationRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private BookingBusinessRepository bookingBusinessRepository;

    private static final String BOOKING_BUSINESS_ENDPOINT = "/booking-business";

    private final List<UUID> createdBookingIds = new ArrayList<>();
    private final List<UUID> createdUserIds = new ArrayList<>();
    private final List<UUID> createdWorkStationIds = new ArrayList<>();
    private final List<UUID> createdCompanyIds = new ArrayList<>();
    private final List<UUID> createBuildingIds = new ArrayList<>();
    private final List<UUID> createFloorIds = new ArrayList<>();
    private final List<UUID> createdBookingBusinessIds = new ArrayList<>();

    @Test
    void getBookingsByBusinessBookingIdAndType() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID businessBookingId = savedBooking.getBookingBusiness().getId();
        String type = savedBooking.getWorkStation().getType();

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/business_user/{businessBookingId}", businessBookingId)
                                .param("type", type)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userResource.id", is(savedBooking.getUser().getId().toString())))
                .andExpect(jsonPath("$[0].workStationType", is(type)));
    }

    @Test
    void getBookingsByBusinessBookingIdAndType_NoType() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID businessBookingId = savedBooking.getBookingBusiness().getId();

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/business_user/{businessBookingId}", businessBookingId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userResource.id", is(savedBooking.getUser().getId().toString())));
    }

    @Test
    void getBookingsByBusinessBookingIdAndType_NonExistentBusinessBookingId() throws Exception {
        UUID nonExistentBusinessBookingId = UUID.randomUUID();
        String type = "desk";

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/business_user/{businessBookingId}", nonExistentBusinessBookingId)
                                .param("type", type)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void cancelBookingsByIds() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        List<UUID> bookingIds = List.of(savedBooking.getId());
        BookingBusinessListDeleteDto bookingBusinessListDeleteDto = new BookingBusinessListDeleteDto();
        bookingBusinessListDeleteDto.setBookingIds(bookingIds);

        mvc.perform(
                        put(BOOKING_BUSINESS_ENDPOINT + "/business_user/delete")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(bookingBusinessListDeleteDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookingId", is(savedBooking.getId().toString())))
                .andExpect(jsonPath("$[0].status", is("canceled")));
    }

    @Test
    void cancelBookingsByIds_Multiple() throws Exception {
        Booking savedBooking1 = savedBookingTemplate();
        Booking savedBooking2 = savedBookingTemplate();
        List<UUID> bookingIds = List.of(savedBooking1.getId(), savedBooking2.getId());
        BookingBusinessListDeleteDto bookingBusinessListDeleteDto = new BookingBusinessListDeleteDto();
        bookingBusinessListDeleteDto.setBookingIds(bookingIds);

        mvc.perform(
                        put(BOOKING_BUSINESS_ENDPOINT + "/business_user/delete")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(bookingBusinessListDeleteDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookingId", is(savedBooking1.getId().toString())))
                .andExpect(jsonPath("$[0].status", is("canceled")))
                .andExpect(jsonPath("$[1].bookingId", is(savedBooking2.getId().toString())))
                .andExpect(jsonPath("$[1].status", is("canceled")));
    }

    @Test
    void getBookingsByBusinessBookingIdAndType_NonExistentType() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID businessBookingId = savedBooking.getBookingBusiness().getId();
        String nonExistentType = "nonexistent_type";

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/business_user/{businessBookingId}", businessBookingId)
                                .param("type", nonExistentType)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void cancelBookingsByIds_NonExistentBooking() throws Exception {
        UUID nonExistentBookingId = UUID.randomUUID();
        BookingBusinessListDeleteDto bookingBusinessListDeleteDto = new BookingBusinessListDeleteDto();
        bookingBusinessListDeleteDto.setBookingIds(List.of(nonExistentBookingId));

        mvc.perform(
                        put(BOOKING_BUSINESS_ENDPOINT + "/business_user/delete")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(bookingBusinessListDeleteDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].bookingId", is(nonExistentBookingId.toString())))
                .andExpect(jsonPath("$[0].status", is("canceled")));
    }

    @Test
    void getBookingsByBusinessBookingIdAndType_NoBookings() throws Exception {
        BookingBusiness bookingBusiness = savedBusinessBookingTemplate();
        UUID businessBookingId = bookingBusiness.getId();

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/business_user/{businessBookingId}", businessBookingId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void bookingBusinessFindById() throws Exception {
        BookingBusiness bookingBusiness = savedBusinessBookingTemplate();
        UUID bookingBusinessId = bookingBusiness.getId();

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/{bookingBusinessId}", bookingBusinessId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(bookingBusinessId.toString())))
                .andExpect(jsonPath("$.userId", is(bookingBusiness.getUser().getId().toString())));
    }


    @Test
    void bookingBusinessFindById_NonExistent() throws Exception {
        UUID nonExistentBookingBusinessId = UUID.randomUUID();

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/{bookingBusinessId}", nonExistentBookingBusinessId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBookingBusinessesByUserId() throws Exception {
        BookingBusiness bookingBusiness = savedBusinessBookingTemplate();
        UUID userId = bookingBusiness.getUser().getId();

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/list/{userId}", userId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId", is(userId.toString())));
    }

    @Test
    void getBookingBusinessesByUserId_WithDates() throws Exception {
        BookingBusiness bookingBusiness = savedBusinessBookingTemplate();
        UUID userId = bookingBusiness.getUser().getId();
        String startDate = "2023-01-01";
        String endDate = "2024-12-31";

        mvc.perform(
                        get(BOOKING_BUSINESS_ENDPOINT + "/list/{userId}", userId)
                                .param("startDate", startDate)
                                .param("endDate", endDate)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId", is(userId.toString())));
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


    private BookingBusiness savedBusinessBookingTemplate() {
        Company company = Company.builder()
                .name("ProvaBusiness")
                .vatCode("ddadasda")
                .email("aaa@gmail.com")
                .phone("3335714426")
                .companyCode("Pro505")
                .build();

        companyRepository.saveAndFlush(company);
        createdCompanyIds.add(company.getId());

        User businessUser = User.builder()
                .barrierFreeFlag(true)
                .email("business.user@gmail.com")
                .password("password")
                .name("Luigi")
                .surname("Bianchi")
                .username("businessuser")
                .type("business_user")
                .taxCode("BUSINESS12345")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .company(company)
                .build();

        userRepository.saveAndFlush(businessUser);
        createdUserIds.add(businessUser.getId());

        BookingBusiness bookingBusiness = BookingBusiness.builder()
                .bookingDate(ZonedDateTime.now())
                .user(businessUser)
                .build();

        bookingBusinessRepository.saveAndFlush(bookingBusiness);
        createdBookingBusinessIds.add(bookingBusiness.getId());

        return bookingBusiness;
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

    private Booking savedBookingTemplate() {
        String str = "2024-04-06T10:15:30+01:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(str, formatter);

        Company company = Company.builder()
                .name("ProvaBusiness")
                .vatCode("ddadasda")
                .email("aaa@gmail.com")
                .phone("3335714426")
                .companyCode("Pro505")
                .build();

        companyRepository.saveAndFlush(company);
        createdCompanyIds.add(company.getId());

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
                .company(company)
                .build();

        userRepository.saveAndFlush(user);
        createdUserIds.add(user.getId());

        User businessUser = User.builder()
                .barrierFreeFlag(true)
                .email("business.user@gmail.com")
                .password("password")
                .name("Luigi")
                .surname("Bianchi")
                .username("businessuser")
                .type("business_user")
                .taxCode("BUSINESS12345")
                .registrationDate(ZonedDateTime.now())
                .status("active")
                .company(company)
                .build();

        userRepository.saveAndFlush(businessUser);
        createdUserIds.add(businessUser.getId());

        Building building = Building.builder()
                .name("Building1")
                .address("Via Roma 1")
                .city("Milano")
                .province("MI")
                .streetNumber(2)
                .build();

        buildingRepository.saveAndFlush(building);
        createBuildingIds.add(building.getId());

        Floor floor = Floor.builder()
                .numberOfFloor(2)
                .building(building)
                .build();

        floorRepository.saveAndFlush(floor);
        createFloorIds.add(floor.getId());

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3.0)
                .equipment("monitor")
                .type("desk")
                .building(building)
                .floor(floor)
                .build();

        workStationRepository.saveAndFlush(workStation);
        createdWorkStationIds.add(workStation.getId());

        BookingBusiness bookingBusiness = BookingBusiness.builder()
                .bookingDate(ZonedDateTime.now())
                .user(businessUser)
                .build();

        bookingBusinessRepository.saveAndFlush(bookingBusiness);
        createdBookingBusinessIds.add(bookingBusiness.getId());

        Booking booking = Booking.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime.plusHours(1))
                .checkIn(null)
                .checkOut(null)
                .status("active")
                .hasPenalty(false)
                .user(user)
                .workStation(workStation)
                .bookingBusiness(bookingBusiness)
                .build();

        Booking savedBooking = bookingRepository.saveAndFlush(booking);
        createdBookingIds.add(booking.getId());

        return savedBooking;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void cleanUp() {
        createdBookingIds.forEach(id -> bookingRepository.deleteById(id));
        createdUserIds.forEach(id -> userRepository.deleteById(id));
        createdWorkStationIds.forEach(id -> workStationRepository.deleteById(id));
        createdCompanyIds.forEach(id -> companyRepository.deleteById(id));
        createBuildingIds.forEach(id -> buildingRepository.deleteById(id));
        createFloorIds.forEach(id -> floorRepository.deleteById(id));
        createdBookingBusinessIds.forEach(id -> bookingBusinessRepository.deleteById(id));
        createdBookingIds.clear();
        createdUserIds.clear();
        createdWorkStationIds.clear();
        createdCompanyIds.clear();
        createBuildingIds.clear();
        createFloorIds.clear();
        createdBookingBusinessIds.clear();
    }
}

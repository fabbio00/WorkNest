package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.dto.BookingEditDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = BookingControllerTest.class)
@Transactional
class BookingControllerTest extends BaseMvcTest {

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

    private static final String BOOKING_ENDPOINT = "/bookings";

    private final List<UUID> createdBookingIds = new ArrayList<>();
    private final List<UUID> createdUserIds = new ArrayList<>();
    private final List<UUID> createdWorkStationIds = new ArrayList<>();
    private final List<UUID> createdCompanyIds = new ArrayList<>();
    private final List<UUID> createBuildingIds = new ArrayList<>();
    private final List<UUID> createFloorIds = new ArrayList<>();

    @Test
    void getBookingById() throws Exception {
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
    void getBookingsByCompanyId() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID companyId = savedBooking.getUser().getCompany().getId();
        String employeeName = savedBooking.getUser().getName();
        String employeeSurname = savedBooking.getUser().getSurname();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list_by_company/{companyId}", companyId)
                                .param("employeeName", employeeName)
                                .param("employeeSurname", employeeSurname)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userResource.name", is(employeeName)))
                .andExpect(jsonPath("$[0].userResource.surname", is(employeeSurname)));
    }

    @Test
    void getBookingsByCompanyId_NoBookings() throws Exception {
        Company savedCompany = savedCompanyTemplate();
        UUID companyId = savedCompany.getId();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list_by_company/{companyId}", companyId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getBookingsByCompanyId_NonExistentCompany() throws Exception {
        UUID nonExistentCompanyId = UUID.randomUUID();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list_by_company/{nonExistentCompanyId}", nonExistentCompanyId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getBookingsByCompanyId_NoEmployeeNameSurname() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID companyId = savedBooking.getUser().getCompany().getId();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list_by_company/{companyId}", companyId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
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



    @Test
    void getBookingsByCompanyId_NonMatchingEmployeeNameSurname() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID companyId = savedBooking.getUser().getCompany().getId();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list_by_company/{companyId}", companyId)
                                .param("employeeName", "NonMatchingName")
                                .param("employeeSurname", "NonMatchingSurname")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void createBooking() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingCreateDto bookingCreateDto = bookingDtoCreation();
        String bookingCreateJson = objectMapper.writeValueAsString(bookingCreateDto);

        mvc.perform(
                        post("/bookings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookingCreateJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startDate").value(bookingCreateDto.getStartDate().toInstant().toString()))
                .andExpect(jsonPath("$.endDate").value(bookingCreateDto.getEndDate().toInstant().toString()))
                .andExpect(jsonPath("$.user.id", is(bookingCreateDto.getUserId().toString())))
                .andExpect(jsonPath("$.workStation.id", is(bookingCreateDto.getWorkStationId().toString())));
    }

    @Test
    void editBooking() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Booking savedBooking = savedBookingTemplate();
        String str = "2024-05-06T10:15:30+01:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime newZonedDateTime = ZonedDateTime.parse(str, formatter).withZoneSameInstant(ZoneOffset.UTC);

        BookingEditDto bookingEditDto = BookingEditDto.builder()
                .startDate(newZonedDateTime)
                .endDate(newZonedDateTime.plusHours(1))
                .workStationId(savedBooking.getWorkStation().getId())
                .build();
        String bookingEditJson = objectMapper.writeValueAsString(bookingEditDto);

        mvc.perform(
                        put("/bookings/edit/{bookingId}", savedBooking.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookingEditJson)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startDate", is(bookingEditDto.getStartDate().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))))
                .andExpect(jsonPath("$.endDate", is(bookingEditDto.getEndDate().format(DateTimeFormatter.ISO_ZONED_DATE_TIME))));
    }










    @Test
    void getBookingsByCompanyId_WithDateRange() throws Exception {
        Booking savedBooking = savedBookingTemplate();
        UUID companyId = savedBooking.getUser().getCompany().getId();
        String employeeName = savedBooking.getUser().getName();
        String employeeSurname = savedBooking.getUser().getSurname();
        LocalDate startDate = savedBooking.getStartDate().toLocalDate();
        LocalDate endDate = savedBooking.getEndDate().toLocalDate();

        mvc.perform(
                        get(BOOKING_ENDPOINT + "/list_by_company/{companyId}", companyId)
                                .param("startDate", startDate.toString())
                                .param("endDate", endDate.toString())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userResource.name", is(employeeName)))
                .andExpect(jsonPath("$[0].userResource.surname", is(employeeSurname)))
                .andExpect(jsonPath("$[0].startDate", containsString(startDate.toString())))
                .andExpect(jsonPath("$[0].endDate", containsString(endDate.toString())));
    }

    private Company savedCompanyTemplate() {
        Company company = Company.builder()
                .name("ProvaBusiness")
                .vatCode("ddadasda")
                .email("aaa@gmail.com")
                .phone("3335714426")
                .companyCode("Pro505")
                .build();
        Company savedCompany = companyRepository.saveAndFlush(company);
        createdCompanyIds.add(company.getId());

        return savedCompany;
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

        Booking savedBooking = bookingRepository.saveAndFlush(booking);
        createdBookingIds.add(booking.getId());

        return savedBooking;
    }

    private BookingCreateDto bookingDtoCreation() {
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

        user = userRepository.saveAndFlush(user);
        createdUserIds.add(user.getId());

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3.0)
                .equipment("monitor")
                .type("desk")
                .build();

        workStation = workStationRepository.saveAndFlush(workStation);
        createdWorkStationIds.add(workStation.getId());

        return BookingCreateDto.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime)
                .status("active")
                .hasPenalty(false)
                .userId(user.getId())
                .workStationId(workStation.getId())
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

        user = userRepository.saveAndFlush(user);
        createdUserIds.add(user.getId());

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3.0)
                .equipment("monitor")
                .type("desk")
                .build();

        workStation = workStationRepository.saveAndFlush(workStation);
        createdWorkStationIds.add(workStation.getId());

        return BookingCreateDto.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime)
                .status("active")
                .hasPenalty(false)
                .userId(user.getId())
                .workStationId(workStation.getId())
                .build();
    }

    @AfterEach
    void cleanUp() {
        createdBookingIds.forEach(id -> bookingRepository.deleteById(id));
        createdUserIds.forEach(id -> userRepository.deleteById(id));
        createdWorkStationIds.forEach(id -> workStationRepository.deleteById(id));
        createdCompanyIds.forEach(id -> companyRepository.deleteById(id));
        createBuildingIds.forEach(id -> buildingRepository.deleteById(id));
        createFloorIds.forEach(id -> floorRepository.deleteById(id));
        createdBookingIds.clear();
        createdUserIds.clear();
        createdWorkStationIds.clear();
        createdCompanyIds.clear();
        createBuildingIds.clear();
        createFloorIds.clear();
    }
}

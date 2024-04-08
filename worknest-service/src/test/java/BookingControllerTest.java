import com.ams.worknest.model.dto.BookingCreateDto;
import com.ams.worknest.model.entities.Booking;
import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.BookingFindWorkStationResource;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.UserRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@ContextConfiguration(classes = BookingControllerTest.class)
public class BookingControllerTest extends BaseMvcTest{

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
                .floor(1)
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
                .floor(1)
                .build();

        WorkStation savedWorkStation = workStationRepository.save(workStation);

        return BookingCreateDto.builder()
                .startDate(zonedDateTime)
                .endDate(zonedDateTime)
                .status("active")
                .hasPenalty(false)
                .userId(savedUser.getId())
                .workstationId(savedWorkStation.getId())
                .build();
    }




}

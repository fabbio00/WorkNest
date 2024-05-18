package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.repositories.WorkStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = BookingControllerTest.class)
class WorkStationControllerTest extends BaseMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WorkStationRepository workStationRepository;

    private static final String WORKSTATION_ENDPOINT = "/workstations";

    @Test
    void getWorkStationById() throws Exception{
        WorkStation savedWorkStation = savedWorkStationTemplate();
        mvc.perform(
                        get(WORKSTATION_ENDPOINT + "/{workStationId}", savedWorkStation.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(savedWorkStation.getName())))
                .andReturn();
    }

    @AfterEach
    void cleanUp() throws Exception {
        workStationRepository.deleteAll();
    }

    WorkStation savedWorkStationTemplate(){

        WorkStation workStation = WorkStation.builder()
                .name("WorkStation1")
                .pricePerH(3)
                .equipment("monitor")
                .type("desk")
                .build();

        return workStationRepository.save(workStation);

    }

}

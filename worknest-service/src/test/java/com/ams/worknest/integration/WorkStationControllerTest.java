package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.entities.Building;
import com.ams.worknest.model.entities.Floor;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.repositories.BuildingRepository;
import com.ams.worknest.repositories.FloorRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = WorkStationControllerTest.class)
class WorkStationControllerTest extends BaseMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private WorkStationRepository workStationRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    private static final String WORKSTATION_ENDPOINT = "/workstations";

    private final List<UUID> createdWorkstationIds = new ArrayList<>();
    private final List<UUID> createdFloorIds = new ArrayList<>();
    private final List<UUID> createdBuildingIds = new ArrayList<>();

    private Floor testFloor;
    private Building testBuilding;

    @BeforeEach
    void setUp() {
        // Create a Building and Floor for testing
        testBuilding = buildingRepository.save(
                Building.builder()
                        .name("Test Building")
                        .city("Test City")
                        .streetNumber(123)
                        .address("Test Address")
                        .province("Test Province").build());
        createdBuildingIds.add(testBuilding.getId());

        testFloor = floorRepository.save(Floor.builder()
                .numberOfFloor(1)
                .numBathrooms(2)
                .building(testBuilding)
                .build());
        createdFloorIds.add(testFloor.getId());
    }

    @Test
    void getWorkStationById() throws Exception {
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

    @Test
    void getWorkStationList() throws Exception {
        WorkStation savedWorkStation1 = savedWorkStationTemplate();
        savedWorkStationTemplate("WorkStation2", "laptop", false);

        mvc.perform(
                        get(WORKSTATION_ENDPOINT)
                                .param("floorId", testFloor.getId().toString())
                                .param("buildingId", testBuilding.getId().toString())
                                .param("equipment", "monitor")
                                .param("isPresentWindow", "true")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.workStationResourceList", hasSize(1)))
                .andExpect(jsonPath("$.workStationResourceList[0].name", is(savedWorkStation1.getName())))
                .andReturn();
    }


    @AfterEach
    void cleanUp() {
        createdWorkstationIds.forEach(id -> workStationRepository.deleteById(id));
        createdFloorIds.forEach(id -> floorRepository.deleteById(id));
        createdBuildingIds.forEach(id -> buildingRepository.deleteById(id));
        createdWorkstationIds.clear();
        createdFloorIds.clear();
        createdBuildingIds.clear();
    }

    WorkStation savedWorkStationTemplate() {
        return savedWorkStationTemplate("WorkStation1", "monitor", true);
    }

    WorkStation savedWorkStationTemplate(String name, String equipment, boolean isPresentWindow) {
        WorkStation workStation = WorkStation.builder()
                .name(name)
                .pricePerH(3.0)
                .equipment(equipment)
                .type("desk")
                .floor(testFloor)
                .building(testBuilding)
                .isPresentWindow(isPresentWindow)
                .build();

        WorkStation savedWorkStation = workStationRepository.save(workStation);
        createdWorkstationIds.add(savedWorkStation.getId());
        return savedWorkStation;
    }
}

package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.entities.Building;
import com.ams.worknest.model.entities.Floor;
import com.ams.worknest.repositories.BookingRepository;
import com.ams.worknest.repositories.BuildingRepository;
import com.ams.worknest.repositories.FloorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@ContextConfiguration(classes = BookingControllerTest.class)
class FloorControllerTest extends BaseMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    private static final String FLOOR_ENDPOINT = "/floors";

    private final List<UUID> createdFloorIds = new ArrayList<>();
    private final List<UUID> createdBuildingIds = new ArrayList<>();

    @Test
    void getFloorById() throws Exception{

        Building building = Building.builder()
                .name("Building1")
                .city("City1")
                .address("Address1")
                .province("Province1")
                .streetNumber(123)
                .build();

        Building savedBuilding = buildingRepository.save(building);
        createdBuildingIds.add(savedBuilding.getId());

        Floor savedFloor = savedFloorTemplate(savedBuilding);
        mvc.perform(
                        get(FLOOR_ENDPOINT + "/{floorId}", savedFloor.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.buildingId", is(savedFloor.getBuilding().getId().toString())))
                .andReturn();
    }
    @Test
    void getFloorsByBuildingId() throws Exception {
        Building building = Building.builder()
                .name("Building1")
                .city("City1")
                .address("Address1")
                .province("Province1")
                .streetNumber(123)
                .build();

        Building savedBuilding = buildingRepository.save(building);
        createdBuildingIds.add(savedBuilding.getId());

        Floor savedFloor1 = savedFloorTemplate(savedBuilding);
        Floor savedFloor2 = savedFloorTemplate(savedBuilding);

        mvc.perform(
                        get(FLOOR_ENDPOINT + "/list/{buildingId}", savedBuilding.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].buildingId", is(savedBuilding.getId().toString())))
                .andExpect(jsonPath("$[1].buildingId", is(savedBuilding.getId().toString())))
                .andReturn();
    }

    @Test
    void getFloorByNonExistentId() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        mvc.perform(
                        get(FLOOR_ENDPOINT + "/{floorId}", nonExistentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getFloorsByNonExistentBuildingId() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        mvc.perform(
                        get(FLOOR_ENDPOINT + "/list/{buildingId}", nonExistentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }


    Floor savedFloorTemplate(Building savedBuilding){
        Floor floor = Floor.builder()
                .numberOfFloor(1)
                .numBathrooms(2)
                .building(savedBuilding)
                .build();

        Floor savedFloor = floorRepository.save(floor);
        createdFloorIds.add(savedFloor.getId());
        return savedFloor;
    }

    @AfterEach
    void cleanUp() {
        createdFloorIds.forEach(id -> floorRepository.deleteById(id));
        createdBuildingIds.forEach(id -> buildingRepository.deleteById(id));

        createdFloorIds.clear();
        createdBuildingIds.clear();
    }

}
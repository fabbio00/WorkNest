package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.entities.Building;
import com.ams.worknest.repositories.BuildingRepository;
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
@ContextConfiguration(classes = BuildingControllerTest.class)
class BuildingControllerTest extends BaseMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private BuildingRepository buildingRepository;

    private static final String BUILDING_ENDPOINT = "/buildings";

    private final List<UUID> createdBuildingIds = new ArrayList<>();

    @Test
    void getBuildingById() throws Exception{
        Building savedBuilding = savedBuildingTemplate();
        mvc.perform(
                        get(BUILDING_ENDPOINT + "/{buildingId}", savedBuilding.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(savedBuilding.getId().toString())))
                .andReturn();
    }

    @Test
    void getBuildingsList() throws Exception {
        savedBuildingTemplate();
        mvc.perform(
                        get(BUILDING_ENDPOINT + "/list")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getBuildingByNonExistentId() throws Exception {
        UUID nonExistentId = UUID.randomUUID();

        mvc.perform(
                        get(BUILDING_ENDPOINT + "/{buildingId}", nonExistentId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    Building savedBuildingTemplate(){
        Building building = Building.builder()
                .name("Building1")
                .city("City1")
                .address("Address1")
                .province("Province1")
                .streetNumber(123)
                .build();

        Building savedBuilding = buildingRepository.save(building);
        createdBuildingIds.add(savedBuilding.getId());
        return savedBuilding;
    }

    @AfterEach
    void cleanUp() {
        createdBuildingIds.forEach(id -> buildingRepository.deleteById(id));
        createdBuildingIds.clear();
    }
}
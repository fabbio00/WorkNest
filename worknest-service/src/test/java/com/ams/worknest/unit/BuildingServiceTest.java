package com.ams.worknest.unit;

import com.ams.worknest.model.entities.Building;
import com.ams.worknest.model.entities.Floor;
import com.ams.worknest.model.resources.BuildingFindResource;
import com.ams.worknest.repositories.BuildingRepository;
import com.ams.worknest.services.impl.BuildingServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuildingServiceTest {

    @InjectMocks
    private BuildingServiceImpl buildingService;

    @Mock
    private BuildingRepository buildingRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test find building by id")
    @Test
    void testFindBuildingById() {
        UUID buildingId = UUID.randomUUID();
        Building building = new Building();
        building.setId(buildingId);
        building.setName("Test Building");

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));

        BuildingFindResource foundBuilding = buildingService.findBuildingById(buildingId);

        assertEquals(buildingId, foundBuilding.getId());
        assertEquals(building.getName(), foundBuilding.getName());
    }

    @DisplayName("Test get buildings list")
    @Test
    void testGetBuildingsList() {
        List<Building> buildings = new ArrayList<>();

        Building building1 = new Building();
        building1.setId(UUID.randomUUID());
        building1.setName("Building 1");
        buildings.add(building1);

        Building building2 = new Building();
        building2.setId(UUID.randomUUID());
        building2.setName("Building 2");
        buildings.add(building2);

        when(buildingRepository.findAll()).thenReturn(buildings);

        List<BuildingFindResource> foundBuildings = buildingService.getBuildingsList();

        assertEquals(2, foundBuildings.size());
        assertEquals(building1.getId(), foundBuildings.get(0).getId());
        assertEquals(building2.getId(), foundBuildings.get(1).getId());

        // Add additional assertions to check that the floors list is not null
        assertNotNull(foundBuildings.get(0).getFloors());
        assertNotNull(foundBuildings.get(1).getFloors());
    }

    @DisplayName("Test find building by id with non-existing id")
    @Test
    void testFindBuildingByIdNonExisting() {
        UUID buildingId = UUID.randomUUID();

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> buildingService.findBuildingById(buildingId));
    }

    @DisplayName("Test get buildings list with no buildings")
    @Test
    void testGetBuildingsListNoBuildings() {
        when(buildingRepository.findAll()).thenReturn(new ArrayList<>());

        List<BuildingFindResource> foundBuildings = buildingService.getBuildingsList();

        assertEquals(0, foundBuildings.size());
    }

    @DisplayName("Test find building by id with null id")
    @Test
    void testFindBuildingByIdNull() {
        assertThrows(EntityNotFoundException.class, () -> buildingService.findBuildingById(null));
    }

    @DisplayName("Test get buildings list when repository throws exception")
    @Test
    void testGetBuildingsListException() {
        when(buildingRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> buildingService.getBuildingsList());
    }

    @DisplayName("Test find building by id when repository throws exception")
    @Test
    void testFindBuildingByIdException() {
        UUID buildingId = UUID.randomUUID();

        when(buildingRepository.findById(buildingId)).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> buildingService.findBuildingById(buildingId));
    }
}
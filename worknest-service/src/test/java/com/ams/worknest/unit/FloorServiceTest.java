package com.ams.worknest.unit;

import com.ams.worknest.model.entities.Floor;
import com.ams.worknest.model.entities.Building;
import com.ams.worknest.model.resources.FloorFindResource;
import com.ams.worknest.repositories.FloorRepository;
import com.ams.worknest.services.impl.FloorServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FloorServiceTest {

    @InjectMocks
    private FloorServiceImpl floorService;

    @Mock
    private FloorRepository floorRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test find floor by id")
    @Test
    void testFindFloorById() {
        UUID floorId = UUID.randomUUID();
        Floor floor = new Floor();
        floor.setId(floorId);
        Building building = new Building();
        building.setId(UUID.randomUUID());
        floor.setBuilding(building);

        when(floorRepository.findById(floorId)).thenReturn(Optional.of(floor));

        FloorFindResource foundFloor = floorService.findFloorById(floorId);

        assertEquals(floorId, foundFloor.getFloorId());
        assertEquals(building.getId(), foundFloor.getBuildingId());
    }

    @DisplayName("Test find floors by building id")
    @Test
    void testFindFloorsByBuildingId() {
        UUID buildingId = UUID.randomUUID();
        List<Floor> floors = new ArrayList<>();

        Floor floor1 = new Floor();
        floor1.setId(UUID.randomUUID());
        Building building1 = new Building();
        building1.setId(buildingId);
        floor1.setBuilding(building1);
        floors.add(floor1);

        Floor floor2 = new Floor();
        floor2.setId(UUID.randomUUID());
        Building building2 = new Building();
        building2.setId(buildingId);
        floor2.setBuilding(building2);
        floors.add(floor2);

        when(floorRepository.findByBuildingId(buildingId)).thenReturn(floors);

        List<FloorFindResource> foundFloors = floorService.findFloorsByBuildingId(buildingId);

        assertEquals(2, foundFloors.size());
        assertEquals(floor1.getId(), foundFloors.get(0).getFloorId());
        assertEquals(floor2.getId(), foundFloors.get(1).getFloorId());
    }

    @DisplayName("Test find floor by id with non-existing id")
    @Test
    void testFindFloorByIdNonExisting() {
        UUID floorId = UUID.randomUUID();

        when(floorRepository.findById(floorId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> floorService.findFloorById(floorId));
    }

    @DisplayName("Test find floors by building id with no floors")
    @Test
    void testFindFloorsByBuildingIdNoFloors() {
        UUID buildingId = UUID.randomUUID();

        when(floorRepository.findByBuildingId(buildingId)).thenReturn(new ArrayList<>());

        assertThrows(RuntimeException.class, () -> floorService.findFloorsByBuildingId(buildingId));
    }

    @DisplayName("Test find floor by id with null id")
    @Test
    void testFindFloorByIdNull() {
        assertThrows(EntityNotFoundException.class, () -> floorService.findFloorById(null));
    }

    @DisplayName("Test find floors by building id with null id")
    @Test
    void testFindFloorsByBuildingIdNull() {
        assertThrows(EntityNotFoundException.class, () -> floorService.findFloorsByBuildingId(null));
    }

    @DisplayName("Test find floors by building id with non-existing id")
    @Test
    void testFindFloorsByBuildingIdNonExisting() {
        UUID buildingId = UUID.randomUUID();

        when(floorRepository.findByBuildingId(buildingId)).thenReturn(new ArrayList<>());

        assertThrows(EntityNotFoundException.class, () -> floorService.findFloorsByBuildingId(buildingId));
    }
}
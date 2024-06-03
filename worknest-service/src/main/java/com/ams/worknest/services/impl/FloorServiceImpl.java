package com.ams.worknest.services.impl;

import com.ams.worknest.model.entities.Floor;
import com.ams.worknest.model.resources.FloorFindResource;
import com.ams.worknest.repositories.FloorRepository;
import com.ams.worknest.services.FloorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link FloorService} interface.
 * Provides methods for finding floor details.
 */
@Component
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private static final String FLOOR_NOT_FOUND = "Floor not found!";

    /**
     * Finds a floor by its unique identifier.
     *
     * @param floorId The unique identifier of the floor.
     * @return A resource representing the found floor.
     * @throws RuntimeException if the floor is not found.
     */
    @Override
    public FloorFindResource findFloorById(UUID floorId) {

        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(() -> new EntityNotFoundException(FLOOR_NOT_FOUND));

        return FloorFindResource.builder()
                .floorId(floorId)
                .buildingId(floor.getBuilding().getId())
                .numberOfFloor(floor.getNumberOfFloor())
                .numBathrooms(floor.getNumBathrooms())
                .build();
    }

    /**
     * Finds floors by building id.
     *
     * @param buildingId The unique identifier of the building.
     * @return A list of resources representing the floors found.
     * @throws EntityNotFoundException if no floors are found for the given building id.
     */
    @Override
    public List<FloorFindResource> findFloorsByBuildingId(UUID buildingId) {

        List<Floor> floors = floorRepository.findByBuildingId(buildingId);

        if (floors.isEmpty()) {
            throw new EntityNotFoundException("Floors not found for building ID: " + buildingId);
        }

        return floors.stream().map(
                floor -> FloorFindResource.builder()
                        .floorId(floor.getId())
                        .numberOfFloor(floor.getNumberOfFloor())
                        .numBathrooms(floor.getNumBathrooms())
                        .buildingId(floor.getBuilding().getId())
                        .build())
                .toList();
    }

}
package com.ams.worknest.services.impl;

import com.ams.worknest.model.entities.Building;
import com.ams.worknest.model.resources.BuildingFindResource;
import com.ams.worknest.model.resources.FloorFindResource;
import com.ams.worknest.repositories.BuildingRepository;
import com.ams.worknest.services.BuildingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link BuildingService} interface.
 * Provides methods for finding building details.
 */
@Component
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private static final String BUILDING_NOT_FOUND = "Building not found!";

    /**
     * Finds a building by its unique identifier.
     *
     * @param buildingId The unique identifier of the building.
     * @return A resource representing the found building.
     * @throws EntityNotFoundException if the building is not found.
     */
    @Override
    public BuildingFindResource findBuildingById(UUID buildingId) {

        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException(BUILDING_NOT_FOUND));

        List<FloorFindResource> floorResources = new ArrayList<>();
        if (building.getFloors() != null) {
            floorResources = building.getFloors().stream()
                    .map(floor -> FloorFindResource.builder()
                            .floorId(floor.getId())
                            .buildingId(building.getId())
                            .numberOfFloor(floor.getNumberOfFloor())
                            .numBathrooms(floor.getNumBathrooms())
                            .build())
                    .toList();
        }

        return BuildingFindResource.builder()
                .id(building.getId())
                .name(building.getName())
                .city(building.getCity())
                .province(building.getProvince())
                .address(building.getAddress())
                .streetNumber(building.getStreetNumber())
                .floors(floorResources)
                .build();
    }

    /**
     * Retrieves a list of all buildings.
     *
     * @return A list of resources representing all buildings.
     */
    @Override
    public List<BuildingFindResource> getBuildingsList() {

        List<Building> buildingsList = buildingRepository.findAll();

        return buildingsList.stream().map(building -> {
            List<FloorFindResource> floorResources = new ArrayList<>();
            if (building.getFloors() != null) {
                floorResources = building.getFloors().stream()
                        .map(floor -> FloorFindResource.builder()
                                .floorId(floor.getId())
                                .buildingId(building.getId())
                                .numberOfFloor(floor.getNumberOfFloor())
                                .numBathrooms(floor.getNumBathrooms())
                                .build())
                        .toList();
            }


            return BuildingFindResource.builder()
                    .id(building.getId())
                    .name(building.getName())
                    .city(building.getCity())
                    .province(building.getProvince())
                    .address(building.getAddress())
                    .streetNumber(building.getStreetNumber())
                    .floors(floorResources)
                    .build();
        }).toList();
    }

}

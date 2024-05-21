package com.ams.worknest.services;

import com.ams.worknest.model.resources.BuildingFindResource;

import java.util.List;
import java.util.UUID;

/**
 * BuildingService interface.
 * This interface handles the business logic for the Building entity.
 */
public interface BuildingService {

    /**
     * Finds a building by its unique identifier.
     *
     * @param buildingId The unique identifier of the building.
     * @return A resource representing the found building.
     */
    BuildingFindResource findBuildingById(UUID buildingId);

    /**
     * Retrieves a list of all buildings.
     *
     * @return A list of resources representing all buildings.
     */
    List<BuildingFindResource> getBuildingsList();

}
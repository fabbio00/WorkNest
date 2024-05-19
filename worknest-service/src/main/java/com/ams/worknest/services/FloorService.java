package com.ams.worknest.services;

import com.ams.worknest.model.resources.FloorFindResource;

import java.util.List;
import java.util.UUID;

/**
 * FloorService interface.
 * This interface handles the business logic for the Floor entity.
 *
 */
public interface FloorService {

    /**
     * Finds a floor by its unique identifier.
     *
     * @param floorId The unique identifier of the floor.
     * @return A resource representing the found floor.
     */
    FloorFindResource findFloorById(UUID floorId);

    /**
     * Finds floors by building id.
     *
     * @param buildingId The unique identifier of the building.
     * @return A list of resources representing the floors found.
     */
    List<FloorFindResource> findFloorsByBuildingId(UUID buildingId);

}

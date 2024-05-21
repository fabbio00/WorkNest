package com.ams.worknest.services;

import com.ams.worknest.model.resources.WorkStationListResource;
import com.ams.worknest.model.resources.WorkStationResource;

import java.util.UUID;

/**
 * WorkStationService interface.
 * This interface handles the business logic for the WorkStation entity.
 */
public interface WorkStationService {

    /**
     * Retrieves a workstation by its unique identifier.
     *
     * @param workStationId The unique identifier of the workstation.
     * @return A resource representing the found workstation.
     */
    WorkStationResource getWorkStationById(UUID workStationId);

    /**
     * Retrieves a list of workstations based on various criteria.
     *
     * @param floorId The unique identifier of the floor.
     * @param buildingId The unique identifier of the building.
     * @param equipment The equipment available at the workstation.
     * @param isPresentWindow Indicates if there is a window present near the workstation.
     * @return A list of resources representing the workstations found.
     */
    WorkStationListResource getWorkStationList(UUID floorId,
                                               UUID buildingId,
                                               String equipment,
                                               Boolean isPresentWindow);
}
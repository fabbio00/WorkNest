package com.ams.worknest.model.resources;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * WorkStationListResource class.
 * Represents a list of workstations in a specific building and floor.
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WorkStationListResource {

    /**
     * The unique identifier of the building.
     */
    private UUID buildingId;

    /**
     * The name of the building.
     */
    private String buildingName;

    /**
     * The unique identifier of the floor.
     */
    private UUID floorId;

    /**
     * The number of the floor in the building.
     */
    private int numberOfFloor;

    /**
     * The list of workstations on the floor.
     */
    private List<WorkStationResource> workStationResourceList;

}

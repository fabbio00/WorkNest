package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

/**
 * FloorFindResource class.
 * Represents a floor resource that is returned to UI when retrieving floor information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FloorFindResource {

    /**
     * The unique identifier of the floor.
     */
    private UUID floorId;

    /**
     * The unique identifier of the building that the floor belongs to.
     */
    private UUID buildingId;

    /**
     * The number of the floor in the building.
     */
    private int numberOfFloor;

    /**
     * The number of bathrooms on the floor.
     */
    private int numBathrooms;

}




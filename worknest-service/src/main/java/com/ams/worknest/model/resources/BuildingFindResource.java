package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * BuildingFindResource class.
 * Represents a building resource displayed in the UI after retrieving building information.
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BuildingFindResource {

    /**
     * The unique identifier of the building.
     */
    private UUID id;

    /**
     * The name of the building.
     */
    private String name;

    /**
     * The city where the building is located.
     */
    private String city;

    /**
     * The province where the building is located.
     */
    private String province;

    /**
     * The address of the building.
     */
    private String address;

    /**
     * The street number of the building.
     */
    private Integer streetNumber;

    /**
     * The list of floors in the building.
     */
    private List<FloorFindResource> floors;

}


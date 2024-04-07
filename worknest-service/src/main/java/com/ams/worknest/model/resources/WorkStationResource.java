package com.ams.worknest.model.resources;

import lombok.*;

import java.util.UUID;

/**
 * Resource class representing a workstation.
 * Contains details about a workstation.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WorkStationResource {

    /**
     * The unique identifier of the workstation.
     */
    private UUID id;

    /**
     * The name of the workstation.
     */
    private String name;

    /**
     * The price per hour of the workstation.
     */
    private double pricePerH;

    /**
     * The equipment available at the workstation.
     */
    private String equipment;

    /**
     * The type of the workstation.
     */
    private String type;

    /**
     * The floor where the workstation is located.
     */
    private int floor;

}

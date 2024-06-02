package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

/**
 * Resource class representing a workstation.
 * Contains details about a workstation.
 *
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
     * The x-coordinate of the workstation's position.
     */
    private float cx;

    /**
     * The y-coordinate of the workstation's position.
     */
    private float cy;

    /**
     * The number of seats at the workstation.
     */
    private int numberOfSeats;

    /**
     * Indicates if the workstation is in a left position.
     */
    private boolean isLeftPosition;

    /**
     * Indicates if there is a window present near the workstation.
     */
    private boolean isPresentWindow;

}

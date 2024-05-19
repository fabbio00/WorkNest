package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * WorkStation entity class.
 * Represents a workstation on a floor in a building in the system.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "workstation")
public class WorkStation {

    /**
     * The unique identifier of the workstation.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * The name of the workstation.
     */
    private String name;

    /**
     * The price per hour for using the workstation.
     */
    @Column(name = "price_per_h")
    private double pricePerH;

    /**
     * Equipment available at the workstation.
     */
    private String equipment;

    /**
     * The type of workstation.
     */
    private String type;

    /**
     * The x-coordinate of the workstation's position.
     */
    private Float cx;

    /**
     * The y-coordinate of the workstation's position.
     */
    private Float cy;

    /**
     * The number of seats at the workstation.
     */
    @Column(name = "number_of_seats")
    private int numberOfSeats;

    /**
     * Indicates if the workstation is in a left position.
     */
    @Column(name = "is_left_position")
    private Boolean isLeftPosition;

    /**
     * Indicates if there is a window present near the workstation.
     */
    @Column(name = "is_present_window")
    private Boolean isPresentWindow;

    /**
     * The building associated with the workstation.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    /**
     * The floor associated with the workstation.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

}

package com.ams.worknest.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Entity class representing a workstation.
 * Contains details of a workstation available for booking.
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
     * The floor where the workstation is located.
     */
    private int floor;

}

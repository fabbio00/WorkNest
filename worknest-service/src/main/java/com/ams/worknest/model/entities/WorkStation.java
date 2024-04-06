package com.ams.worknest.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "workstation")
public class WorkStation {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;

    @Column(name = "price_per_h")
    private double pricePerH;

    private String equipment;

    private String type;

    private int floor;


}

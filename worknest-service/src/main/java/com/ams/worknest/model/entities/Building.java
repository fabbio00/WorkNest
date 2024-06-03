package com.ams.worknest.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * Building entity class.
 * Represents a building in the system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Building {

    /**
     * The unique identifier of the building.
     */
    @Id
    @GeneratedValue(generator = "uuid")
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
    @Column(name = "street_number")
    private Integer streetNumber;

    /**
     * The list of floors in the building.
     */
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<Floor> floors;

}
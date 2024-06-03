package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * Floor entity class.
 * Represents a floor in a building in the system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Floor {

    /**
     * The unique identifier of the floor.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * The number of the floor in the building.
     */
    @Column(name = "number_of_floor")
    private int numberOfFloor;

    /**
     * The number of bathrooms on the floor.
     */
    @Column(name = "num_bathrooms")
    private int numBathrooms;

    /**
     * The building that this floor belongs to.
     */
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "building_id")
    private Building building;

    /**
     * The list of workstations on this floor.
     */
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkStation> workstations;

}

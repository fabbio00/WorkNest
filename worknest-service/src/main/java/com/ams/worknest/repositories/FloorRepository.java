package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * FloorRepository interface.
 * This interface handles the data access layer for the Floor entity.
 *
 * @author OCR1D
 */
public interface FloorRepository extends JpaRepository<Floor, UUID> {

    /**
     * Finds floors by building id.
     *
     * @param buildingId The unique identifier of the building.
     * @return A list of floors associated with the given building id.
     */
    List<Floor> findByBuildingId(UUID buildingId);

}
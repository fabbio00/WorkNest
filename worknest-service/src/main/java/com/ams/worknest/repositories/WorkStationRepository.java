package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.WorkStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

/**
 * WorkStationRepository interface.
 * This interface handles the data access layer for the WorkStation entity.
 */
public interface WorkStationRepository extends JpaRepository<WorkStation, UUID> {

    /**
     * Custom query to find workstations by various criteria.
     *
     * @param floorId The unique identifier of the floor.
     * @param buildingId The unique identifier of the building.
     * @param equipment The equipment available at the workstation.
     * @param isPresentWindow Indicates if there is a window present near the workstation.
     * @return A list of workstations that match the given criteria.
     */
    @Query("SELECT ws FROM WorkStation ws " +
            "WHERE (:floorId IS NULL OR ws.floor.id = :floorId) " +
            "AND (:buildingId IS NULL OR ws.building.id = :buildingId) " +
            "AND (:equipment IS NULL OR ws.equipment LIKE %:equipment%) " +
            "AND (:isPresentWindow IS NULL OR ws.isPresentWindow = :isPresentWindow)")
    List<WorkStation> findByCriteria(@Param("floorId") UUID floorId,
                                     @Param("buildingId") UUID buildingId,
                                     @Param("equipment") String equipment,
                                     @Param("isPresentWindow") Boolean isPresentWindow);
}

package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * BuildingRepository interface.
 * This interface handles the data access layer for the Building entity.
 */
public interface BuildingRepository extends JpaRepository<Building, UUID> {

}

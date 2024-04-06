package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.WorkStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkStationRepository extends JpaRepository<WorkStation, UUID> {



}

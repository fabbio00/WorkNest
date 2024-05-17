package com.ams.worknest.services.impl;

import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.WorkStationResource;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.WorkStationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Implementation of the WorkStationService interface.
 * This class provides functionality to retrieve details of workstations.
 * It retrieves details such as name, price, equipment, type, and floor of a workstation.
 * If a requested workstation is not found, it throws an EntityNotFoundException.
 */

@Component
@RequiredArgsConstructor
public class WorkStationServiceImpl implements WorkStationService {

    /**
     * The repository for accessing workstation data from the database.
     */
    private final WorkStationRepository workStationRepository;

    /**
     * Retrieves the details of a workstation by its unique identifier.
     *
     * @param workStationId the unique identifier of the workstation
     * @return the details of the workstation as a WorkStationResource object
     * @throws EntityNotFoundException if the workstation with the specified ID is not found
     */
    @Override
    public WorkStationResource getWorkStationById(UUID workStationId) {
        WorkStation workStation = workStationRepository.findById(workStationId)
                .orElseThrow(() -> new EntityNotFoundException("WorkStation not found with ID: " + workStationId));

        return WorkStationResource.builder()
                .id(workStation.getId())
                .pricePerH(workStation.getPricePerH())
                .equipment(workStation.getEquipment())
                .type(workStation.getType())
                .name(workStation.getName())
                .build();
    }

}

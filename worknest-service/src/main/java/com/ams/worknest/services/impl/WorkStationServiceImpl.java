package com.ams.worknest.services.impl;

import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.WorkStationResource;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.WorkStationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WorkStationServiceImpl implements WorkStationService {

    private final WorkStationRepository workStationRepository;

    @Override
    public WorkStationResource getWorkStationById(UUID workStationId) {
        WorkStation workStation = workStationRepository.findById(workStationId)
                .orElseThrow(() -> new EntityNotFoundException("WorkStation not found with ID: " + workStationId));

        return WorkStationResource.builder()
                .id(workStation.getId())
                .pricePerH(workStation.getPricePerH())
                .equipment(workStation.getEquipment())
                .type(workStation.getType())
                .floor(workStation.getFloor())
                .name(workStation.getName())
                .build();
    }

}

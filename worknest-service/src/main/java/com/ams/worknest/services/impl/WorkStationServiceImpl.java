package com.ams.worknest.services.impl;

import com.ams.worknest.model.entities.Building;
import com.ams.worknest.model.entities.Floor;
import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.WorkStationListResource;
import com.ams.worknest.model.resources.WorkStationResource;
import com.ams.worknest.repositories.FloorRepository;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.repositories.BuildingRepository;
import com.ams.worknest.services.WorkStationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Implementation of the {@link WorkStationService} interface.
 * Provides methods for retrieving workstation details.
 */
@Component
@RequiredArgsConstructor
public class WorkStationServiceImpl implements WorkStationService {

    /**
     * The repository for accessing workstation data from the database.
     */
    private final WorkStationRepository workStationRepository;

    /**
     * The repository for accessing building data from the database.
     */
    private final BuildingRepository buildingRepository;

    /**
     * The repository for accessing floor data from the database.
     */
    private final FloorRepository floorRepository;

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
                .numberOfSeats(workStation.getNumberOfSeats())
                .cx(workStation.getCx() != null ? workStation.getCx() : 0.0f)
                .cy(workStation.getCy() != null ? workStation.getCy() : 0.0f)
                .isLeftPosition(workStation.getIsLeftPosition() != null && workStation.getIsLeftPosition())
                .isPresentWindow(workStation.getIsPresentWindow() != null && workStation.getIsPresentWindow())
                .build();
    }

    /**
     * Retrieves a list of workstations based on various criteria.
     *
     * @param floorId The unique identifier of the floor.
     * @param buildingId The unique identifier of the building.
     * @param equipment The equipment available at the workstation.
     * @param isPresentWindow Indicates if there is a window present near the workstation.
     * @return A list of resources representing the workstations found.
     * @throws EntityNotFoundException if no workstations are found for the given criteria.
     */
    @Override
    public WorkStationListResource getWorkStationList(UUID floorId,
                                                      UUID buildingId,
                                                      String equipment,
                                                      Boolean isPresentWindow) {

        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new EntityNotFoundException("Building not found with ID: " + buildingId));

        Floor floor = floorRepository.findById(floorId)
                .orElseThrow(() -> new EntityNotFoundException("Floor not found with ID: " + floorId));

        List<WorkStation> workStations = workStationRepository.findByCriteria(
                floorId, buildingId, equipment, isPresentWindow
        );

        if (workStations.isEmpty()) {
            throw new EntityNotFoundException("No WorkStations found for the given criteria.");
        }

        List<WorkStationResource> workStationResources = workStations.stream().
                map(workStation -> WorkStationResource.builder()
                        .id(workStation.getId())
                        .name(workStation.getName())
                        .pricePerH(workStation.getPricePerH())
                        .equipment(workStation.getEquipment())
                        .type(workStation.getType())
                        .cx(workStation.getCx() != null ? workStation.getCx() : 0.0f)
                        .cy(workStation.getCy() != null ? workStation.getCy() : 0.0f)
                        .isLeftPosition(workStation.getIsLeftPosition() != null && workStation.getIsLeftPosition())
                        .isPresentWindow(workStation.getIsPresentWindow() != null && workStation.getIsPresentWindow())
                        .numberOfSeats(workStation.getNumberOfSeats())
                        .build())
                .toList();

        return WorkStationListResource.builder()
                .buildingId(building.getId())
                .buildingName(building.getName())
                .floorId(floor.getId())
                .numberOfFloor(floor.getNumberOfFloor())
                .workStationResourceList(workStationResources)
                .build();
    }

}

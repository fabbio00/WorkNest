package com.ams.worknest.controllers;

import com.ams.worknest.model.resources.WorkStationListResource;
import com.ams.worknest.model.resources.WorkStationResource;
import com.ams.worknest.services.WorkStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * WorkStationController class.
 * Provides endpoints for managing workstations.
 *
 */
@RestController
@Slf4j
@RequestMapping(value = "/workstations", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class WorkStationController {

    private final WorkStationService workStationService;

    /**
     * Retrieve a workstation by its unique identifier.
     *
     * @param workStationId the UUID of the workstation to retrieve
     * @return the requested workstation as a resource
     */
    @GetMapping("/{workStationId}")
    @ResponseStatus(HttpStatus.OK)
    public WorkStationResource getWorkStationById(@PathVariable("workStationId") UUID workStationId){
        return workStationService.getWorkStationById(workStationId);
    }

    /**
     * Retrieves a list of workstations based on the provided parameters.
     *
     * @param floorId the UUID of the floor
     * @param buildingId the UUID of the building
     * @param equipment the equipment present in the workstation
     * @param isPresentWindow the presence of a window near the workstation
     * @return a list of workstation resources based on the provided parameters
     */
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public WorkStationListResource getWorkStationList(@RequestParam("floorId") UUID floorId,
                                                      @RequestParam("buildingId")  UUID buildingId,
                                                      @RequestParam(value = "equipment", required = false) String equipment,
                                                      @RequestParam(value = "isPresentWindow", required = false) Boolean isPresentWindow
                                                      ) {
        return workStationService.getWorkStationList(floorId, buildingId, equipment, isPresentWindow);
    }

}

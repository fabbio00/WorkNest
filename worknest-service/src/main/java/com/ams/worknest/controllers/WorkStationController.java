package com.ams.worknest.controllers;

import com.ams.worknest.model.resources.WorkStationResource;
import com.ams.worknest.services.WorkStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for managing workstations.
 * Provides endpoints for retrieving workstation details.
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

}

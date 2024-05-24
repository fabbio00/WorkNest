package com.ams.worknest.controllers;

import com.ams.worknest.model.resources.*;
import com.ams.worknest.services.BuildingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * Building Controller class
 * Controller for managing Building.
 * Provides endpoints for creating and retrieving building details.
 */
@Slf4j
@RestController
@RequestMapping(value = "/buildings", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    /**
     * Retrieve a building by its unique identifier.
     *
     * @param buildingId the UUID of the building to retrieve
     * @return the requested building as a resource
     */
    @GetMapping("/{buildingId}")
    @ResponseStatus(HttpStatus.OK)
    public BuildingFindResource buildingFindById(@PathVariable("buildingId") UUID buildingId){
        return buildingService.findBuildingById(buildingId);
    }

    /**
     * Retrieves a list of buildings.
     *
     * @return a list of building resources
     */
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<BuildingFindResource> getBuildingsList(){
        return buildingService.getBuildingsList();
    }

}

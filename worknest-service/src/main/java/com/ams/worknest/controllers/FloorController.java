package com.ams.worknest.controllers;

import com.ams.worknest.model.resources.*;
import com.ams.worknest.services.FloorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * FloorController class.
 * Provides endpoints for managing floors of a building.
 */
@Slf4j
@RestController
@RequestMapping(value = "/floors", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    /**
     * Retrieve a floor by its unique identifier.
     *
     * @param floorId the UUID of the floor to retrieve
     * @return the requested floor as a resource
     */
    @GetMapping("/{floorId}")
    @ResponseStatus(HttpStatus.OK)
    public FloorFindResource floorFindById(@PathVariable("floorId") UUID floorId){
        return floorService.findFloorById(floorId);
    }

    /**
     * Retrieves a list of floors associated with a specific building.
     *
     * @param buildingId the UUID of the building whose floors are to be retrieved
     * @return a list of floor resources associated with the specified building
     */
    @GetMapping("/list/{buildingId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FloorFindResource> getFloorsByBuildingId(@PathVariable("buildingId") UUID buildingId){
        return floorService.findFloorsByBuildingId(buildingId);
    }

}

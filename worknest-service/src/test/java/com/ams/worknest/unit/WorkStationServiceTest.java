package com.ams.worknest.unit;

import com.ams.worknest.model.entities.WorkStation;
import com.ams.worknest.model.resources.WorkStationResource;
import com.ams.worknest.repositories.WorkStationRepository;
import com.ams.worknest.services.impl.WorkStationServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WorkStationServiceTest {

    @InjectMocks



    private WorkStationServiceImpl workStationService;

    @Mock
    private WorkStationRepository workStationRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Get workstation by id returns correct workstation")
    @Test
     void getWorkStationByIdReturnsCorrectWorkStation() {
        UUID workStationId = UUID.randomUUID();
        WorkStation workStation = new WorkStation();
        workStation.setId(workStationId);

        when(workStationRepository.findById(workStationId)).thenReturn(Optional.of(workStation));

        WorkStationResource response = workStationService.getWorkStationById(workStationId);

        WorkStationResource expected = convertToResource(workStation);

        assertEquals(expected.getId(), response.getId());
    }

    private WorkStationResource convertToResource(WorkStation workStation) {
        WorkStationResource workStationResource = new WorkStationResource();
        workStationResource.setId(workStation.getId());

        return workStationResource;
    }


    @DisplayName("Get workstation by id throws exception when workstation not found")
    @Test
     void getWorkStationByIdThrowsExceptionWhenWorkStationNotFound() {
        UUID workStationId = UUID.randomUUID();

        when(workStationRepository.findById(workStationId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> workStationService.getWorkStationById(workStationId));
    }
}
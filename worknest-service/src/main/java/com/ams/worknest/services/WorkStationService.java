package com.ams.worknest.services;

import com.ams.worknest.model.resources.WorkStationResource;

import java.util.UUID;

public interface WorkStationService {
    WorkStationResource getWorkStationById(UUID workStationId);
}

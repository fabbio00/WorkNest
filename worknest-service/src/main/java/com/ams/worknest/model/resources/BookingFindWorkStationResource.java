package com.ams.worknest.model.resources;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class BookingFindWorkStationResource {

    private UUID workStationId;

}

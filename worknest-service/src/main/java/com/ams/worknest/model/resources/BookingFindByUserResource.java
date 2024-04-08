package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.WorkStation;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class BookingFindByUserResource {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private ZonedDateTime checkIn;

    private ZonedDateTime checkOut;

    private String status;

    private UUID workStationId;

}

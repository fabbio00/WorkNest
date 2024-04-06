package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.WorkStation;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder(toBuilder = true)
public class BookingFindResource {

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private ZonedDateTime checkIn;

    private ZonedDateTime checkOut;

    private String status;

    private boolean hasPenalty;

    private WorkStation workStation;

}

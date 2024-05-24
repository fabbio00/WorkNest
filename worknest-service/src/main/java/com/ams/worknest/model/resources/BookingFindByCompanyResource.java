package com.ams.worknest.model.resources;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingFindByCompanyResource {

    private UUID bookingId;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private ZonedDateTime checkIn;

    private ZonedDateTime checkOut;

    private String status;

    private Boolean hasPenalty;

    private String workStationName;

    private Double workstationCostPerHour;

    private String buildingName;

    private String floorName;

    private UserResource userResource;

}


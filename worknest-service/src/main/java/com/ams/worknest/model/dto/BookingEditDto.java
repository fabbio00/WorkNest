package com.ams.worknest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the details to be edited for a booking.
 * Contains information such as the new start date, end date, and workstation ID.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEditDto {

    /**
     * The new start date for the booking.
     */
    private ZonedDateTime startDate;

    /**
     * The new end date for the booking.
     */
    private ZonedDateTime endDate;

    /**
     * The ID of the workstation to which the booking is to be updated.
     */
    private UUID workStationId;


}

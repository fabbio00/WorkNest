package com.ams.worknest.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;


/**
 * Data transfer object (DTO) for creating a booking.
 * Contains details required to create a booking.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingCreateDto {

    /**
     * The start date and time of the booking.
     */
    private ZonedDateTime startDate;

    /**
     * The end date and time of the booking.
     */
    private ZonedDateTime endDate;

    /**
     * The status of the booking.
     */
    private String status;

    /**
     * Indicates whether the booking has a penalty associated with it.
     */
    private boolean hasPenalty;

    /**
     * The unique identifier of the user associated with the booking.
     */
    private UUID userId;

    /**
     * The unique identifier of the workstation associated with the booking.
     */
    private UUID workStationId;

}

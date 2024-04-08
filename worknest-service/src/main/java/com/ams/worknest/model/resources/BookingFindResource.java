package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.WorkStation;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Resource class representing the details of a booking found.
 * Contains information about an existing booking.
 */
@Data
@Builder(toBuilder = true)
public class BookingFindResource {

    /**
     * The start date and time of the booking.
     */
    private ZonedDateTime startDate;

    /**
     * The end date and time of the booking.
     */
    private ZonedDateTime endDate;

    /**
     * The check-in date and time of the booking.
     */
    private ZonedDateTime checkIn;

    /**
     * The check-out date and time of the booking.
     */
    private ZonedDateTime checkOut;

    /**
     * The status of the booking.
     */
    private String status;

    /**
     * Indicates if the booking has a penalty.
     */
    private boolean hasPenalty;

    /**
     * The workstation associated with the booking.
     */
    private WorkStation workStation;

}

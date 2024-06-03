package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Resource class representing booking details associated with a specific user.
 * Contains information about bookings made by a user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingFindByUserResource {

    /**
     * The UUID of the booking.
     */
    private UUID bookingId;

    /**
     * The start date of the booking.
     */
    private ZonedDateTime startDate;

    /**
     * The end date of the booking.
     */
    private ZonedDateTime endDate;

    /**
     * The check-in date and time for the booking.
     */
    private ZonedDateTime checkIn;

    /**
     * The check-out date and time for the booking.
     */
    private ZonedDateTime checkOut;

    /**
     * The status of the booking.
     */
    private String status;

    /**
     * The UUID of the associated work station for the booking.
     */
    private UUID workStationId;

}

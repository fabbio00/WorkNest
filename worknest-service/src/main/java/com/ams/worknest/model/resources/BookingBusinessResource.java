package com.ams.worknest.model.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Resource class representing the details of a business booking.
 * Contains information about a booking business.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingBusinessResource {

    /**
     * The unique identifier of the booking business.
     */
    private UUID id;

    /**
     * The date and time of the booking.
     */
    private ZonedDateTime bookingDate;

    /**
     * The unique identifier of the user associated with the booking business.
     */
    private UUID userId;
}

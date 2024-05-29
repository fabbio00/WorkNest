package com.ams.worknest.model.resources;

import lombok.*;

import java.util.UUID;

/**
 * Resource class representing the details of a booking business.
 * Contains the ID of the booking business.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingBusinessCreateResource {

    /**
     * The UUID of the booking business.
     */
    private UUID bookingBusinessId;

}

package com.ams.worknest.model.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

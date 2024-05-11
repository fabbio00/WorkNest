package com.ams.worknest.model.resources;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


/**
 * Resource class representing a workstation found during a booking search.
 * Contains the ID of a workstation associated with a booking.
 */
@Data
@Builder(toBuilder = true)
public class BookingFindWorkStationResource {

    /**
     * The unique identifier of the workstation found.
     */
    private UUID workStationId;

    /**
     * The status of the booking associated with the workstation.
     */
    private String status;

}

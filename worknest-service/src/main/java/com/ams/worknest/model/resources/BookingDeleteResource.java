package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

/**
 * Resource class representing the details of a deleted booking.
 * Contains the ID of the deleted booking and its status.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingDeleteResource {

    /**
     * The UUID of the deleted booking.
     */
    private UUID bookingId;

    /**
     * The status of the deletion process.
     */
    private String status;

}

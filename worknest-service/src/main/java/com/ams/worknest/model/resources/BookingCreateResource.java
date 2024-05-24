package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.User;
import com.ams.worknest.model.entities.WorkStation;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * Resource class representing the details of a booking to be created.
 * Contains information required for creating a booking.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingCreateResource {

    /**
     * The start date and time of the booking.
     */
    private ZonedDateTime startDate;

    /**
     * The end date and time of the booking.
     */
    private ZonedDateTime endDate;

    /**
     * The user associated with the booking.
     */
    private User user;

    /**
     * The workstation associated with the booking.
     */
    private WorkStation workStation;

}

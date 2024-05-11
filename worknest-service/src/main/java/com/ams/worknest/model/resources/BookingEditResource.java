package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.WorkStation;
import lombok.*;

import java.time.ZonedDateTime;

/**
 * Resource class representing the details of a booking after editing.
 * Contains information such as the new start date, end date, and updated workstation details.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingEditResource {

    /**
     * The new start date for the booking after editing.
     */
    private ZonedDateTime startDate;

    /**
     * The new end date for the booking after editing.
     */
    private ZonedDateTime endDate;

    /**
     * The updated workstation details for the booking after editing.
     */
    private WorkStation workStation;
}

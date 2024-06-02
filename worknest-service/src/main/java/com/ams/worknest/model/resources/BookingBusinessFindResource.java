package com.ams.worknest.model.resources;

import com.ams.worknest.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.ZonedDateTime;

/**
 * Resource class representing the details of a group of booking.
 * Contains the business user who made the booking and the date of the booking.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingBusinessFindResource {

    /**
     * The business user who made the booking.
     */
    private User user;

    /**
     * The date of the booking.
     */
    private ZonedDateTime bookingDate;

}

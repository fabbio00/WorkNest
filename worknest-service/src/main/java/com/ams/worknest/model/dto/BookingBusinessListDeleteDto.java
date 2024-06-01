package com.ams.worknest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for deleting a list of bookings in a business context.
 * This class is used to transfer data between different parts of the application.
 * It includes a list of UUIDs representing the bookings to be deleted.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingBusinessListDeleteDto {

    /**
     * A list of unique identifiers (UUIDs) representing the bookings to be deleted.
     */
    private List<UUID> bookingIds;

}

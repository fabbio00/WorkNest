package com.ams.worknest.model.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Data transfer object (DTO) for creating a booking-business.
 * Contains details required to create a booking-business.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BookingBusinessCreateDto {

    /**
     * The date of the booking.
     */
    private ZonedDateTime bookingDate;

    /**
     * The unique identifier of the business user associated with the booking.
     */
    private UUID userId;

}

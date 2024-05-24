package com.ams.worknest.model.resources;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Resource class representing the booking information associated with a company.
 * This class is used to transfer booking data between different layers of the application,
 * especially for finding bookings by company.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingFindByCompanyResource {

    /**
     * The unique identifier of the booking.
     */
    private UUID bookingId;

    /**
     * The start date and time of the booking.
     */
    private ZonedDateTime startDate;

    /**
     * The end date and time of the booking.
     */
    private ZonedDateTime endDate;

    /**
     * The check-in date and time of the booking.
     */
    private ZonedDateTime checkIn;

    /**
     * The check-out date and time of the booking.
     */
    private ZonedDateTime checkOut;

    /**
     * The status of the booking.
     */
    private String status;

    /**
     * The flag indicating whether the booking has a penalty.
     */
    private Boolean hasPenalty;

    /**
     * The name of the workstation associated with the booking.
     */
    private String workStationName;

    /**
     * The cost per hour of the workstation associated with the booking.
     */
    private Double workstationCostPerHour;

    /**
     * The name of the building associated with the booking.
     */
    private String buildingName;

    /**
     * The name of the floor associated with the booking.
     */
    private String floorName;

    /**
     * The user resource associated with the booking.
     */
    private UserResource userResource;

}
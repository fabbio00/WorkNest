package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Entity class representing a booking business.
 * Contains details of a booking made by a business user for employee.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingBusiness {

    /**
     * The unique identifier of the booking business.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * The date and time of the booking.
     */
    @Column(name = "booking_date")
    private ZonedDateTime bookingDate;

    /**
     * The user who made the booking.
     */
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}

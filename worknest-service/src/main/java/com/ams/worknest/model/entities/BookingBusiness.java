package com.ams.worknest.model.entities;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;


import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Entity class representing a business booking.
 * Contains details of a business booking that may include multiple individual bookings.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingBusiness {

    /**
     * The unique identifier of the business booking.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * The booking date and time.
     */
    @Column(name = "booking_date")
    private ZonedDateTime bookingDate;

    /**
     * The user associated with the business booking.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The list of bookings associated with this business booking.
     */
    @OneToMany(mappedBy = "bookingBusiness", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
}

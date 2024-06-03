package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Entity class representing a booking.
 * Contains details of a booking made by a user for a workstation.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Booking {

    /**
     * The unique identifier of the booking.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * The start date and time of the booking.
     */
    @Column(name = "start_date")
    private ZonedDateTime startDate;

    /**
     * The end date and time of the booking.
     */
    @Column(name = "end_date")
    private ZonedDateTime endDate;

    /**
     * The check-in date and time of the booking.
     */
    @Column(name = "check_in")
    private ZonedDateTime checkIn;

    /**
     * The check-out date and time of the booking.
     */
    @Column(name = "check_out")
    private ZonedDateTime checkOut;

    /**
     * The status of the booking.
     */
    @Column(name = "status")
    private String status;

    /**
     * Indicates whether the booking has a penalty associated with it.
     */
    @Column(name = "has_penalty")
    private Boolean hasPenalty;

    /**
     * The user associated with the booking.
     */
    @JsonManagedReference
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The workstation associated with the booking.
     */
    @JsonManagedReference
    @OneToOne()
    @JoinColumn(name = "workstation_id")
    private WorkStation workStation;

    /**
     * The business booking associated with this booking.
     */
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "booking_business_id")
    private BookingBusiness bookingBusiness;

}

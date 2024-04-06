package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Booking {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "check_in")
    private ZonedDateTime checkIn;

    @Column(name = "check_out")
    private ZonedDateTime checkOut;

    @Column(name = "status")
    private String status;

    @Column(name = "has_penalty")
    private boolean hasPenalty;

    /*
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

     */

    /*
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workstation_id")
    private WorkStation workStation;

     */

}

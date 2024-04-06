package com.ams.worknest.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "worknest_user")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;

    private String surname;

    private String username;

    private String email;

    private String password;

    @Column(name = "tax_code")
    private String taxCode;

    private String type;

    @Column(name = "barrier_free_flag")
    private boolean barrierFreeFlag;

    private ZonedDateTime registrationDate;

    private String status;

}

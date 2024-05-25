package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Entity representing a user in the Worknest system.
 * This class is used to store user data in the database.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "worknest_user")
public class User {

    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * The first name of the user.
     */
    private String name;

    /**
     * The last name of the user.
     */
    private String surname;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The encrypted password of the user.
     */
    private String password;

    /**
     * The tax code of the user.
     */
    @Column(name = "tax_code")
    private String taxCode;

    /**
     * The type of the user, indicating their role within the system.
     */
    private String type;

    /**
     * The flag indicating whether the user requires barrier-free access.
     */
    @Column(name = "barrier_free_flag")
    private boolean barrierFreeFlag;

    /**
     * The date and time when the user registered.
     */
    private ZonedDateTime registrationDate;

    /**
     * The current status of the user.
     */
    private String status;

    /**
     * The company associated with the user.
     */
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;

}
package com.ams.worknest.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Entity representing a user in the Worknest system.
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
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    /**
     * First name of the user.
     */
    private String name;

    /**
     * Last name of the user.
     */
    private String surname;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * The encrypted password for the user.
     * It is stored in a hashed format for security reasons.
     */
    private String password;

    /**
     * Tax code of the user.
     */
    @Column(name = "tax_code")
    private String taxCode;

    /**
     * Type of the user, indicating their role within the system.
     * - Private: An individual user with personal access.
     * - Employee: A standard business or company user.
     * - Business: A business user with additional privileges, like managing or booking for others.
     * - Administrator: A user with full administrative access to the entire Worknest system.
     */
    private String type;

    /**
     * Flag indicating whether the user requires barrier-free access.
     */
    @Column(name = "barrier_free_flag")
    private boolean barrierFreeFlag;

    /**
     * Date and time when the user registered.
     */
    private ZonedDateTime registrationDate;

    /**
     * Current status of the user.
     * Can be 'active' or 'inactive'.
     */
    private String status;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

}

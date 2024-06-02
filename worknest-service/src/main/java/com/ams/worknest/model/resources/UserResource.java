package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Resource class representing the public-facing information of a user.
 * This class is used to encapsulate user data that is safe to expose through the API.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResource {

    /**
     * The unique identifier of the user.
     */
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
     * The email address of the user.
     */
    private String email;

    /**
     * The tax code of the user.
     */
    private String taxCode;

    /**
     * The type of the user.
     */
    private String type;

    /**
     * The flag indicating whether the user requires barrier-free access.
     */
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
     * The username of the user.
     */
    private String username;

    /**
     * The unique identifier of the company associated with the user.
     */
    private UUID companyId;

}
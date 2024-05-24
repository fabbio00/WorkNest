package com.ams.worknest.model.dto;

import lombok.*;

import java.util.UUID;

/**
 * Data Transfer Object representing user information.
 * This class is used to transfer user data between different layers of the application,
 * especially for creating and updating user details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The surname of the user.
     */
    private String surname;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The tax code of the user.
     */
    private String taxCode;

    /**
     * The type of the user.
     */
    private String type;

    /**
     * The flag indicating if the user is barrier-free.
     */
    private boolean barrierFreeFlag;

    /**
     * The status of the user.
     */
    private String status;

    /**
     * The unique identifier of the company associated with the user.
     */
    private UUID companyId;
    
}
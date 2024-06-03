package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

/**
 * Resource class representing the user information associated with a company.
 * This class is used to transfer user data between different layers of the application,
 * especially for finding users by company.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserFindByCompanyResource {

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
     * The type of the user.
     */
    private String type;

    /**
     * The current status of the user.
     */
    private String status;

}
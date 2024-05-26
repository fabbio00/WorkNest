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

    private String name;

    private String surname;

    private String username;

    private String email;

    private String password;

    private String taxCode;

    private String type;

    private boolean barrierFreeFlag;

    private String status;

    private UUID companyId;

}

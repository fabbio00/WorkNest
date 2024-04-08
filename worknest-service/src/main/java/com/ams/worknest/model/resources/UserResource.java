package com.ams.worknest.model.resources;

import lombok.*;

/**
 * Resource class representing the public-facing information of a user.
 * This class is used to encapsulate user data that is safe to expose through the API.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserResource {

    private UUID id;

    private String name;

    private String surname;

    private String email;

    private String tax_code;

    private String type;

    private boolean barrier_free_flag;

    private ZonedDateTime registration_date;

    private String status;

    private String username;

}

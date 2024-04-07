package com.ams.worknest.model.dto;

import lombok.*;

/**
 * Data transfer object for user login credentials.
 * Contains fields for user email and password.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class UserLoggedDto {

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;
}

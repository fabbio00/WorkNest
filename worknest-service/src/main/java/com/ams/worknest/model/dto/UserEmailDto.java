package com.ams.worknest.model.dto;

import lombok.*;

/**
 * Data transfer object for user email.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEmailDto {

    /**
     * The email address of the user.
     */
    private String email;

}

package com.ams.worknest.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.UUID;

/**
 * Data Transfer Object representing user type editing information.
 * This class is used to transfer user type data between different layers of the application,
 * especially for updating user type details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserEditTypeDto {

    /**
     * The unique identifier of the user whose type is to be edited.
     */
    private UUID userId;

    /**
     * The new type of the user.
     */
    private String type;

}
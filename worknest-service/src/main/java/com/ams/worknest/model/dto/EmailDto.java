package com.ams.worknest.model.dto;

import lombok.*;

/**
 * Data Transfer Object (DTO) for email details.
 * Used for transferring email data between layers.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    /**
     * The recipient's email address.
     */
    private String to;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The text content of the email.
     */
    private String text;
}

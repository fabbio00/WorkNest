package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.EmailDto;
import com.ams.worknest.model.resources.EmailResource;
import com.ams.worknest.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for managing email sending operations.
 * Provides an endpoint for sending emails.
 */
@RestController
@RequestMapping(value = "/sendEmail", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailSenderService emailService;

    /**
     * Send an email with the provided email details.
     *
     * @param emailDto the email data transfer object containing the details of the email to be sent
     * @return an email resource indicating the success of the operation
     */
    @PostMapping()
    public EmailResource sendEmail(@RequestBody EmailDto emailDto) {
        emailService.sendSimpleMessage(emailDto.getTo(), emailDto.getSubject(), emailDto.getText());
        return EmailResource.builder()
                .message("Email sent successfully")
                .build();
    }

}


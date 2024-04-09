package com.ams.worknest.services;

/**
 * Service interface for sending emails.
 * Defines the contract for email-sending operations.
 */
public interface EmailSenderService {

    /**
     * Sends a simple text email to the specified recipient.
     *
     * @param to      The recipient's email address.
     * @param subject The subject line of the email.
     * @param text    The text body of the email.
     */
    void sendSimpleMessage(String to, String subject, String text);

}

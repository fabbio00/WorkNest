package com.ams.worknest.services.impl;

import com.ams.worknest.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Implementation of the {@link EmailSenderService} interface.
 * Provides methods for sending email messages.
 */
@Component
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender emailSender;

    private static final String SENDER_EMAIL_ADDRESS = "testerdev149@gmail.com";

    /**
     * Constructs an EmailSenderServiceImpl with the necessary mail sender.
     *
     * @param emailSender The JavaMailSender that will be used to send emails.
     */
    @Autowired
    public EmailSenderServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Sends a simple email message.
     *
     * @param to      The recipient's email address.
     * @param subject The subject of the email.
     * @param text    The text content of the email.
     */
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SENDER_EMAIL_ADDRESS);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}

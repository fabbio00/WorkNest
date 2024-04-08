package com.ams.worknest.services.impl;

import com.ams.worknest.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService{

    private JavaMailSender emailSender;

    private static final String SENDER_EMAIL_ADDRESS = "testerdev149@gmail.com";

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

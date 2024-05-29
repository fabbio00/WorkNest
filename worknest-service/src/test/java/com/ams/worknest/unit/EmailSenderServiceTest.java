package com.ams.worknest.unit;

import com.ams.worknest.services.impl.EmailSenderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmailSenderServiceTest {

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;

    @Mock
    private JavaMailSender javaMailSender;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Test sending a simple message")
    @Test
     void testSendSimpleMessage() {
        String to = "test@email.com";
        String subject = "Test Subject";
        String text = "Test Text";

        emailSenderService.sendSimpleMessage(to, subject, text);

        verify(javaMailSender).send(Mockito.any(SimpleMailMessage.class));
    }

    @DisplayName("Test sending a simple message failure")
    @Test
     void testSendSimpleMessageFailure() {
        String to = "test@email.com";
        String subject = "Test Subject";
        String text = "Test Text";

        doThrow(new MailSendException("")).when(javaMailSender).send(Mockito.any(SimpleMailMessage.class));

        assertThrows(MailSendException.class, () -> emailSenderService.sendSimpleMessage(to, subject, text));
    }

    @DisplayName("Sending emails to multiple recipients successfully")
    @Test
    void sendSimpleMessagesSuccess() {
        List<String> to = Arrays.asList("test1@email.com", "test2@email.com");
        String subject = "Test Subject";
        String text = "Test Text";

        emailSenderService.sendSimpleMessages(to, subject, text);

        verify(javaMailSender, times(1)).send(Mockito.any(SimpleMailMessage.class));
    }

    @DisplayName("Sending emails to multiple recipients failure")
    @Test
    void sendSimpleMessagesFailure() {
        List<String> to = Arrays.asList("test1@email.com", "test2@email.com");
        String subject = "Test Subject";
        String text = "Test Text";

        doThrow(new MailSendException("")).when(javaMailSender).send(Mockito.any(SimpleMailMessage.class));

        assertThrows(MailSendException.class, () -> emailSenderService.sendSimpleMessages(to, subject, text));
    }

}

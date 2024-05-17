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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

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
}

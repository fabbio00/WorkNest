package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Slf4j
@ContextConfiguration(classes = EmailSenderControllerTest.class)
class EmailSenderControllerTest extends BaseMvcTest {

    @Autowired
    private MockMvc mvc;

    private static final String MAIL_ENDPOINT = "/sendEmail";

    @Test
    void sendEmail() throws Exception {
        String emailJson = "{\"to\":\"test@example.com\",\"subject\":\"Test Subject\",\"text\":\"Test message\"}";

        mvc.perform(
                post(MAIL_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Email sent successfully")));
    }

    @Test
    void sendEmails() throws Exception {
        // Prepare a list of emails
        String emailListJson = "{ \"to\": [\"test1@example.com\", \"test2@example.com\"], \"subject\": \"Test Subject\", \"text\": \"Test message\" }";

        // Perform the POST request and check the response
        mvc.perform(
                post("/sendEmail/send-list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailListJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Email sent successfully")));
    }
}

package com.ams.worknest;

import com.ams.worknest.model.dto.EmailDto;
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
@ContextConfiguration(classes = com.ams.worknest.EmailSenderControllerTest.class)
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
}

package com.ams.worknest.integration;

import com.ams.worknest.BaseMvcTest;
import com.ams.worknest.model.dto.CompanyCodeDto;
import com.ams.worknest.model.dto.CompanyDto;
import com.ams.worknest.model.entities.Company;
import com.ams.worknest.repositories.CompanyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@Transactional
@ContextConfiguration(classes = CompanyControllerTest.class)
class CompanyControllerTest extends BaseMvcTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CompanyRepository companyRepository;

    private static final String COMPANY_ENDPOINT = "/companies";

    @Test
    void createCompany() throws Exception {
        CompanyDto companyDto = companyDtoCreation();

        ObjectMapper objectMapper = new ObjectMapper();
        String companyJson = objectMapper.writeValueAsString(companyDto);

        mvc.perform(
                        post(COMPANY_ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(companyJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(companyDto.getName())));
    }

    @Test
    void getCompanyByCompanyCode() throws Exception {
        Company savedCompany = savedCompanyTemplate();
        CompanyCodeDto companyCodeDto = CompanyCodeDto.builder()
                .companyCode(savedCompany.getCompanyCode())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String companyCodeJson = objectMapper.writeValueAsString(companyCodeDto);

        mvc.perform(
                        post(COMPANY_ENDPOINT + "/companyCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(companyCodeJson))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.companyCode", is(savedCompany.getCompanyCode())));
    }


    @Test
    void getCompanyByCompanyCodeNotFound() throws Exception {
        CompanyCodeDto companyCodeDto = CompanyCodeDto.builder()
                .companyCode("NON_EXISTENT_CODE")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String companyCodeJson = objectMapper.writeValueAsString(companyCodeDto);

        mvc.perform(
                        post(COMPANY_ENDPOINT + "/companyCode")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(companyCodeJson))
                .andExpect(status().isOk());
    }

    Company savedCompanyTemplate() {
        Company company = Company.builder()
                .name("Test Company")
                .email("test@company.com")
                .vatCode("IT12345678901")
                .phone("1234567890")
                .companyCode("TEST123")
                .build();

        return companyRepository.save(company);
    }

    CompanyDto companyDtoCreation() {
        return CompanyDto.builder()
                .name("Test Company")
                .email("test@company.com")
                .vatCode("IT12345678901")
                .phone("1234567890")
                .companyCode("TEST123")
                .build();
    }

    @AfterEach
    void cleanUp() throws Exception {
        companyRepository.deleteAll();
    }
}

package com.ams.worknest.unit;

import com.ams.worknest.model.dto.CompanyDto;
import com.ams.worknest.model.dto.CompanyCodeDto;
import com.ams.worknest.model.entities.Company;
import com.ams.worknest.model.resources.CompanyResource;
import com.ams.worknest.repositories.CompanyRepository;
import com.ams.worknest.services.impl.CompanyServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Create company successfully")
    @Test
    void createCompanySuccessfully() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Test Company");
        companyDto.setEmail("test@company.com");
        companyDto.setPhone("1234567890");
        companyDto.setVatCode("IT12345678901");
        companyDto.setCompanyCode("TEST123");

        Company company = new Company();
        company.setId(UUID.randomUUID());
        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setPhone(companyDto.getPhone());
        company.setVatCode(companyDto.getVatCode());
        company.setCompanyCode(companyDto.getCompanyCode());

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        CompanyResource companyResource = companyService.createCompany(companyDto);

        assertEquals(companyDto.getName(), companyResource.getName());
        assertEquals(companyDto.getEmail(), companyResource.getEmail());
        assertEquals(companyDto.getCompanyCode(), companyResource.getCompanyCode());
    }

    @DisplayName("Get company by company code successfully")
    @Test
    void getCompanyByCompanyCodeSuccessfully() {
        String companyCode = "TEST123";

        Company company = new Company();
        company.setId(UUID.randomUUID());
        company.setName("Test Company");
        company.setEmail("test@company.com");
        company.setPhone("1234567890");
        company.setVatCode("IT12345678901");
        company.setCompanyCode(companyCode);

        when(companyRepository.findByCompanyCode(companyCode)).thenReturn(Optional.of(company));

        CompanyCodeDto companyCodeDto = new CompanyCodeDto();
        companyCodeDto.setCompanyCode(companyCode);

        CompanyResource companyResource = companyService.getCompanyByCompanyCode(companyCodeDto);

        assertEquals(company.getName(), companyResource.getName());
        assertEquals(company.getEmail(), companyResource.getEmail());
        assertEquals(company.getCompanyCode(), companyResource.getCompanyCode());
    }

    @DisplayName("Get company by company code - not found")
    @Test
    void getCompanyByCompanyCodeNotFound() {
        String companyCode = "NON_EXISTENT_CODE";

        when(companyRepository.findByCompanyCode(companyCode)).thenReturn(Optional.empty());

        CompanyCodeDto companyCodeDto = new CompanyCodeDto();
        companyCodeDto.setCompanyCode(companyCode);

        assertThrows(EntityNotFoundException.class, () -> {
            companyService.getCompanyByCompanyCode(companyCodeDto);
        });
    }

    CompanyDto companyDtoCreation() {
        return CompanyDto.builder()
                .name("Test Company")
                .email("test@company.com")
                .phone("1234567890")
                .vatCode("IT12345678901")
                .companyCode("TEST123")
                .build();
    }
}

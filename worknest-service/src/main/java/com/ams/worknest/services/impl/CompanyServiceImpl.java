package com.ams.worknest.services.impl;

import com.ams.worknest.model.dto.CompanyDto;
import com.ams.worknest.model.dto.CompanyCodeDto;
import com.ams.worknest.model.entities.Company;
import com.ams.worknest.model.resources.CompanyResource;
import com.ams.worknest.repositories.CompanyRepository;
import com.ams.worknest.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
/**
 * Service implementation for managing company-related operations.
 * This class provides methods to create and retrieve company information.
 */
@Component
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    /**
     * Creates a new company in the database using the information provided in the CompanyDto.
     *
     * @param companyDto Data Transfer Object containing company information.
     * @return CompanyResource containing the public-facing company information.
     */
    public CompanyResource createCompany(CompanyDto companyDto){
        Company company = Company.builder()
                .name(companyDto.getName())
                .email(companyDto.getEmail())
                .phone(companyDto.getPhone())
                .vatCode(companyDto.getVatCode())
                .companyCode(companyDto.getCompanyCode())
                .build();

        Company savedCompany = companyRepository.save(company);

        return CompanyResource.builder()
                .id(savedCompany.getId())
                .name(savedCompany.getName())
                .email(savedCompany.getEmail())
                .companyCode(savedCompany.getCompanyCode())
                .build();
    }

    /**
     * Retrieves a company's public information from the database by their company code.
     *
     * @param companyCodeDto Data Transfer Object containing the company's code.
     * @return CompanyResource containing the public-facing company information.
     */
    public CompanyResource getCompanyByCompanyCode(CompanyCodeDto companyCodeDto){
        Optional<Company> company = companyRepository.findByCompanyCode(companyCodeDto.getCompanyCode());
        CompanyResource companyResource = new CompanyResource();

        company.ifPresent(c -> {
            companyResource.setId(c.getId());
            companyResource.setName(c.getName());
            companyResource.setEmail(c.getEmail());
            companyResource.setVatCode(c.getVatCode());
            companyResource.setPhone(c.getPhone());
            companyResource.setCompanyCode(c.getCompanyCode());
        });
        return companyResource;
    }
}

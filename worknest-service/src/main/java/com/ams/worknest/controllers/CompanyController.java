package com.ams.worknest.controllers;

import com.ams.worknest.model.dto.CompanyCodeDto;
import com.ams.worknest.model.dto.CompanyDto;
import com.ams.worknest.model.resources.CompanyResource;
import com.ams.worknest.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for managing companies.
 * Provides endpoints for creating and retrieving company details.
 */
@Slf4j
@RestController
@RequestMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    /**
     * Create a company with the provided company details.
     *
     * @param companyDto the company data transfer object containing company details
     * @return the created company as a resource
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResource createCompany(@RequestBody CompanyDto companyDto) {
        log.info("POST | /companies | companyDto = {}", companyDto);
        return companyService.createCompany(companyDto);
    }

    /**
     * Retrieve a company by their unique company code.
     *
     * @param companyCodeDto the company code data transfer object containing the company code
     * @return the requested company as a resource
     */
    @PostMapping("/companyCode")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResource getCompanyByCompanyCode(@RequestBody CompanyCodeDto companyCodeDto) {
        log.info("POST | /companies/companyCode | companyCodeDto = {}", companyCodeDto);
        CompanyResource companyResource = companyService.getCompanyByCompanyCode(companyCodeDto);
        if (companyResource == null || companyResource.getCompanyCode() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Company code not found");
        }
        return companyResource;
    }
}
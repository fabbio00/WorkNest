package com.ams.worknest.services;

import com.ams.worknest.model.dto.CompanyCodeDto;
import com.ams.worknest.model.dto.CompanyDto;
import com.ams.worknest.model.resources.CompanyResource;

/**
 * Interface for company service operations.
 * Defines the contract for company-related functionalities such as creating and retrieving companies.
 */
public interface CompanyService {

    /**
     * Creates a new company based on the provided CompanyDto object.
     *
     * @param companyDto the company data transfer object containing the information needed to create a new company.
     * @return CompanyResource containing the public-facing information of the created company.
     */
    CompanyResource createCompany(CompanyDto companyDto);

    /**
     * Retrieves the public-facing information of a company identified by the given company code.
     *
     * @param companyCodeDto the data transfer object containing the company's code.
     * @return CompanyResource containing the public-facing information of the retrieved company.
     */
    CompanyResource getCompanyByCompanyCode(CompanyCodeDto companyCodeDto);

}
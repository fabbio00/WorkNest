package com.ams.worknest.model.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.UUID;

/**
 * Resource class representing the public-facing information of a company.
 * This class is used to encapsulate company data that is safe to expose through the API.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompanyResource {

    /**
     * Unique identifier for the company.
     */
    private UUID id;

    /**
     * Name of the company.
     */
    private String name;

    /**
     * Email address of the company.
     */
    private String email;

    /**
     * VAT code of the company.
     */
    private String vatCode;

    /**
     * Phone number of the company.
     */
    private String phone;

    /**
     * Unique code of the company.
     */
    private String companyCode;

}
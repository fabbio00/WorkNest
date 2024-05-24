package com.ams.worknest.model.dto;

import lombok.*;

/**
 * Data Transfer Object representing company code information.
 * This class is used to transfer company code data between different layers of the application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompanyCodeDto {

    /**
     * The unique code associated with a company.
     */
    private String companyCode;

}
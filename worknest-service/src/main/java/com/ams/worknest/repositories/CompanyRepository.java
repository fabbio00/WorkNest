package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * CompanyRepository interface.
 * This interface handles the data access layer for the Company entity.
 * It extends JpaRepository to provide methods to perform CRUD operations.
 */
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    /**
     * Finds a company by its company code.
     *
     * @param companyCode The company code to search for.
     * @return An Optional containing the company if found, or empty if not found.
     */
    Optional<Company> findByCompanyCode(String companyCode);

}
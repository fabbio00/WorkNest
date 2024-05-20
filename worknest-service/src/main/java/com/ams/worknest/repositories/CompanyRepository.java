package com.ams.worknest.repositories;

import com.ams.worknest.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByCompanyCode(String companyCode);
}

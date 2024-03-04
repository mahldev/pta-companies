package org.iesbelen.pta.companies.infrastructure.repository;

import java.util.Optional;

import org.iesbelen.pta.companies.domain.Company;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.name = ?1")
    Optional<Company> findByName(String name);
}

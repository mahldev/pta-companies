package org.iesbelen.pta.companies.infrastructure.repository;

import java.util.Optional;
import java.util.List;

import org.iesbelen.pta.companies.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT c FROM Company c WHERE c.name = ?1")
    Optional<Company> findByName(String name);

    @Query("SELECT c FROM Company c WHERE c.deleted = false")
    List<Company> findByDeletedFalse();

    @Query("SELECT c FROM Company c WHERE c.sector.id = ?1")
    List<Company> findBySectorId(Long id);

    @Query("SELECT c FROM Company c WHERE c.id = ?1 AND c.deleted = false")
    Optional<Company> findByIdDeletedFalse(Long id);
}

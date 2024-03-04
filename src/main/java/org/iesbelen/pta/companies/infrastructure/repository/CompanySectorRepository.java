package org.iesbelen.pta.companies.infrastructure.repository;

import java.util.Optional;

import org.iesbelen.pta.companies.domain.CompanySector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanySectorRepository extends JpaRepository<CompanySector, Long> {

    @Query("SELECT c FROM CompanySector c WHERE c.name = ?1")
    Optional<CompanySector> findByName(String name);
}

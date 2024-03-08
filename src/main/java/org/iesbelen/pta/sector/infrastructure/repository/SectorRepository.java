package org.iesbelen.pta.sector.infrastructure.repository;

import java.util.Optional;
import java.util.List;

import org.iesbelen.pta.sector.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    @Query("SELECT s FROM Sector s WHERE s.name = ?1")
    Optional<Sector> findByName(String name);

    @Query("SELECT s FROM Sector s WHERE s.deleted = false")
    List<Sector> findByDeletedFalse();

    @Query("SELECT s FROM Sector s WHERE s.id = ?1 AND s.deleted = false")
    Optional<Sector> findByIdDeletedFalse(Long id);
}

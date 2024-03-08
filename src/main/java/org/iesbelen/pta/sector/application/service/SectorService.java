package org.iesbelen.pta.sector.application.service;

import java.util.List;

import org.iesbelen.pta.sector.application.execption.SectorAlreadyExistsExecption;
import org.iesbelen.pta.sector.application.execption.SectorNotFoundExecption;
import org.iesbelen.pta.sector.domain.Sector;
import org.iesbelen.pta.sector.infrastructure.repository.SectorRepository;
import org.iesbelen.pta.companies.infrastructure.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Sector create(Sector sector) {
        if (sectorRepository.findByName(sector.getName()).isPresent()) {
            throw new SectorAlreadyExistsExecption(sector.getName());
        }

        return sectorRepository.save(sector);
    }

    public List<Sector> listAll() {
        return sectorRepository.findByDeletedFalse();
    }

    public Sector find(Long id) {
        return sectorRepository.findByIdDeletedFalse(id)
                .orElseThrow(() -> new SectorNotFoundExecption(id));
    }

    public Sector findByName(String name) {
        return sectorRepository.findByName(name)
                .orElseThrow(() -> new SectorNotFoundExecption(name));
    }

    public Sector update(Long id, Sector sector) {
        sector.setId(id);
        return sectorRepository.findByIdDeletedFalse(id).map(s -> sectorRepository.saveAndFlush(sector))
                .orElseThrow(() -> new SectorNotFoundExecption(id));
    }

    public void remove(Long id) {
        sectorRepository.findById(id).ifPresent(s -> {
            s.setDeleted(true);
            sectorRepository.saveAndFlush(s);

            companyRepository.findBySectorId(id).forEach(c -> {
                c.setDeleted(true);
                companyRepository.saveAndFlush(c);
            });
        });
    }
}

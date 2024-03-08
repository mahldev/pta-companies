package org.iesbelen.pta.companies.application.service;

import java.util.List;

import org.iesbelen.pta.companies.domain.Company;
import org.iesbelen.pta.sector.application.execption.SectorNotFoundExecption;
import org.iesbelen.pta.sector.domain.Sector;
import org.iesbelen.pta.companies.infrastructure.repository.CompanyRepository;
import org.iesbelen.pta.sector.infrastructure.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iesbelen.pta.companies.application.execption.CompanyAlreadyExistException;
import org.iesbelen.pta.companies.application.execption.CompanyNotFoundException;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SectorRepository sectorRepository;

    public Company create(Company company, Sector sector) {
        if (companyRepository.findByName(company.getName()).isPresent()) {
            throw new CompanyAlreadyExistException(company.getName());
        }

        if (sectorRepository.findByName(sector.getName()).isEmpty()) {
            throw new SectorNotFoundExecption(sector.getId());
        }

        company.setSector(sector);
        return companyRepository.saveAndFlush(company);
    }

    public List<Company> listAll() {
        return companyRepository.findByDeletedFalse();
    }

    public Company find(Long id) {
        return companyRepository.findByIdDeletedFalse(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Company update(Long id, Company company) {
        company.setId(id);
        return companyRepository.findByIdDeletedFalse(id).map(c -> companyRepository.saveAndFlush(company))
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public void remove(Long id) {
        companyRepository.findById(id).ifPresent(c -> {
            c.setDeleted(true);
            companyRepository.saveAndFlush(c);
        });
    }
}

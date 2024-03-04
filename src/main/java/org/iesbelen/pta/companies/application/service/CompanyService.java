package org.iesbelen.pta.companies.application.service;

import java.util.List;

import org.iesbelen.pta.companies.domain.Company;
import org.iesbelen.pta.companies.domain.CompanySector;
import org.iesbelen.pta.companies.infrastructure.repository.CompanyRepository;
import org.iesbelen.pta.companies.infrastructure.repository.CompanySectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iesbelen.pta.companies.application.execption.CompanyAlreadyExistException;
import org.iesbelen.pta.companies.application.execption.CompanyNotFoundException;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanySectorRepository companySectorRepository;

    public Company create(Company company, String sectorName) {
        companyRepository.findByName(company.getName()).ifPresent(unused -> {
            throw new CompanyAlreadyExistException(company.getName());
        });

        companySectorRepository.findByName(sectorName).ifPresentOrElse(
                (sector) -> {
                    company.setCompanySector(sector);
                },
                () -> {
                    var newCompanySector = companySectorRepository.saveAndFlush(new CompanySector(sectorName));
                    company.setCompanySector(newCompanySector);
                });

        return companyRepository.saveAndFlush(company);
    }

    public List<Company> listAll() {
        return companyRepository.findAll();
    }

    public Company find(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Company update(Long id, Company company) {
        company.setId(id);
        return companyRepository.findById(id)
                .map(c -> companyRepository.saveAndFlush(company))
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public void remove(Long id) {
        companyRepository.deleteById(id);
    }
}

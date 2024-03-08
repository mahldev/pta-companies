package org.iesbelen.pta.companies.infrastructure.controller;

import java.net.URI;

import org.iesbelen.pta.companies.application.dto.CompanyListResponseDto;
import org.iesbelen.pta.companies.application.dto.CompanyRequestDto;
import org.iesbelen.pta.companies.application.dto.CompanyResponseDto;
import org.iesbelen.pta.companies.application.mapper.CompanyMapper;
import org.iesbelen.pta.companies.application.service.CompanyService;
import org.iesbelen.pta.sector.application.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping({ "", "/" })
    ResponseEntity<CompanyListResponseDto> listAll() {
        var companiesList = companyService.listAll().stream()
                .map(companyMapper::toCompanyResponseDto)
                .toList();
        var companies = new CompanyListResponseDto(companiesList);

        return ResponseEntity.ok(companies);
    }

    @PostMapping({ "", "/" })
    ResponseEntity<CompanyResponseDto> create(
            @RequestBody CompanyRequestDto companyRequestDto) {
        var company = companyMapper.toCompany(companyRequestDto);
        var sector = sectorService.findByName(companyRequestDto.sectorName());
        var createdCompany = companyService.create(company, sector);
        var createdCompanyDto = companyMapper.toCompanyResponseDto(createdCompany);

        return ResponseEntity
                .created(URI.create("/company/" + createdCompany.getId()))
                .body(createdCompanyDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<CompanyResponseDto> find(@PathVariable Long id) {
        var company = companyService.find(id);
        var companyResponseDto = companyMapper.toCompanyResponseDto(company);

        return ResponseEntity.ok(companyResponseDto);
    }

    @PutMapping("/{id}")
    ResponseEntity<CompanyResponseDto> update(
            @PathVariable Long id,
            @RequestBody CompanyRequestDto companyRequestDto) {
        var company = companyMapper.toCompany(companyRequestDto);
        var updatedCompany = companyService.update(id, company);
        var companyResponse = companyMapper.toCompanyResponseDto(updatedCompany);

        return ResponseEntity.ok(companyResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> remove(@PathVariable Long id) {
        companyService.remove(id);

        return ResponseEntity.noContent().build();
    }

}

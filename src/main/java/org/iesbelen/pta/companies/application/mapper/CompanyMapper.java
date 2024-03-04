package org.iesbelen.pta.companies.application.mapper;

import org.iesbelen.pta.companies.application.dto.CompanyRequestDto;
import org.iesbelen.pta.companies.application.dto.CompanyResponseDto;
import org.iesbelen.pta.companies.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    // @Mapping(target = "id", ignore = true)
    // Company toCompany(CompanyResponseDto companyResponseDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companySector", ignore = true)
    Company toCompany(CompanyRequestDto companyRequestDto);

    @Mapping(target = "sectorName", source = "company.companySector.name")
    CompanyResponseDto toCompanyResponseDto(Company company);

    // @Mapping(target = "companySector", ignore = true)
    // CompanyRequestDto toCompanyRequestDto(Company company);
}

package org.iesbelen.pta.companies.application.mapper;

import org.iesbelen.pta.companies.application.dto.CompanyLocationDto;
import org.iesbelen.pta.companies.application.dto.CompanyRequestDto;
import org.iesbelen.pta.companies.application.dto.CompanyResponseDto;
import org.iesbelen.pta.companies.domain.Company;
import org.iesbelen.pta.companies.domain.CompanyLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    final CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Company toCompany(CompanyRequestDto companyRequestDto);

    @Mapping(target = "sectorName", source = "sector.name")
    CompanyResponseDto toCompanyResponseDto(Company company);

    default CompanyLocation map(CompanyLocationDto value) {
        return new CompanyLocation(value.latitude(), value.longitude());
    }

    default CompanyLocationDto map(CompanyLocation value) {
        return new CompanyLocationDto(value.getLatitude(), value.getLongitude());
    }
}

package org.iesbelen.pta.sector.application.mapper;

import java.util.List;

import org.iesbelen.pta.companies.application.dto.CompanyListRequestDto;
import org.iesbelen.pta.companies.application.dto.CompanyResponseDto;
import org.iesbelen.pta.companies.application.mapper.CompanyMapper;
import org.iesbelen.pta.companies.domain.Company;
import org.iesbelen.pta.sector.application.dto.SectorRequestDto;
import org.iesbelen.pta.sector.application.dto.SectorResponseDto;
import org.iesbelen.pta.sector.domain.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SectorMapper {

    final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    final SectorMapper INSTANCE = Mappers.getMapper(SectorMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companies", expression = "java(new java.util.ArrayList<>())")
    @Mapping(target = "deleted", ignore = true)
    Sector toSector(SectorRequestDto sectorRequestDto);

    SectorResponseDto toSectorResponseDto(Sector sector);

    default List<Company> map(CompanyListRequestDto value) {
        return value.companies().stream()
                .map(companyMapper::toCompany)
                .toList();
    }

    default List<CompanyResponseDto> map(List<Company> value) {
        return value.stream()
                .map(companyMapper::toCompanyResponseDto)
                .toList();
    }
}

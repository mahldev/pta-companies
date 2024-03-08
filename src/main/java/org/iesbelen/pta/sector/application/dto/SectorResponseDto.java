package org.iesbelen.pta.sector.application.dto;

import java.util.List;

import org.iesbelen.pta.companies.application.dto.CompanyResponseDto;

public record SectorResponseDto(
        Long id,
        String name,
        List<CompanyResponseDto> companies) {
}

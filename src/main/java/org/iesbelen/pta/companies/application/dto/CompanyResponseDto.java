package org.iesbelen.pta.companies.application.dto;

public record CompanyResponseDto(
        Long id,
        String name,
        String sectorName,
        Integer numberOfEmployees,
        Long revenues,
        String description) {
}

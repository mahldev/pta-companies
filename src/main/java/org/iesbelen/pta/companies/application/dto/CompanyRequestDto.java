package org.iesbelen.pta.companies.application.dto;

public record CompanyRequestDto(
        String name,
        String sectorName,
        Integer numberOfEmployees,
        Long revenues,
        String description,
        CompanyLocationDto location) {
}

package org.iesbelen.pta.companies.application.execption;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super("Company with id " + id + " not found");
    }
}

package org.iesbelen.pta.companies.application.execption;

public class CompanyAlreadyExistException extends RuntimeException {
    public CompanyAlreadyExistException(String companyName) {
        super("The Company with the name '" + companyName + "' already exists");
    }
}

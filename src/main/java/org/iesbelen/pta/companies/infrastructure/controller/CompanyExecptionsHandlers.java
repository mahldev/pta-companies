package org.iesbelen.pta.companies.infrastructure.controller;

import org.iesbelen.pta.companies.application.execption.CompanyAlreadyExistException;
import org.iesbelen.pta.companies.application.execption.CompanyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CompanyExecptionsHandlers {

    record ErrorResponse(String message) {
    };

    @ResponseBody
    @ExceptionHandler(CompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse companyNotFoundHandler(CompanyNotFoundException companyNotFoundException) {
        return new ErrorResponse(companyNotFoundException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(CompanyAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse companyAlreadyExistsHandler(CompanyAlreadyExistException companyAlreadyExistException) {
        return new ErrorResponse(companyAlreadyExistException.getMessage());
    }
}

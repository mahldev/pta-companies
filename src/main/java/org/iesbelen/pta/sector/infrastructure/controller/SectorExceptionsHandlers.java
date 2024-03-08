package org.iesbelen.pta.sector.infrastructure.controller;

import org.iesbelen.pta.sector.application.execption.SectorAlreadyExistsExecption;
import org.iesbelen.pta.sector.application.execption.SectorNotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SectorExceptionsHandlers {

    record ErrorResponse(String message) {
    };

    @ResponseBody
    @ExceptionHandler(SectorNotFoundExecption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponse companyNotFoundHandler(SectorNotFoundExecption sectorNotFoundExecption) {
        return new ErrorResponse(sectorNotFoundExecption.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(SectorAlreadyExistsExecption.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponse companyAlreadyExistsHandler(SectorAlreadyExistsExecption sectorAlreadyExistException) {
        return new ErrorResponse(sectorAlreadyExistException.getMessage());
    }
}

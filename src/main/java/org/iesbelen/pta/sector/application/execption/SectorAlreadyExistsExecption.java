package org.iesbelen.pta.sector.application.execption;

public class SectorAlreadyExistsExecption extends RuntimeException {

    public SectorAlreadyExistsExecption(String sectorName) {
        super("The Company with the name '" + sectorName + "' already exists");
    }

}

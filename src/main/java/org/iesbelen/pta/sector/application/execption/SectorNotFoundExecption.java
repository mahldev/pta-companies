package org.iesbelen.pta.sector.application.execption;

public class SectorNotFoundExecption extends RuntimeException {

    public SectorNotFoundExecption(Long id) {
        super("Sector with id " + id + " not found");
    }

    public SectorNotFoundExecption(String name) {
        super("Sector with id " + name + " not found");
    }
}

package org.iesbelen.pta.sector.infrastructure.controller;

import java.net.URI;

import org.iesbelen.pta.sector.application.dto.SectorListReponseDto;
import org.iesbelen.pta.sector.application.dto.SectorRequestDto;
import org.iesbelen.pta.sector.application.dto.SectorResponseDto;
import org.iesbelen.pta.sector.application.mapper.SectorMapper;
import org.iesbelen.pta.sector.application.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sector")
@CrossOrigin(origins = "http://localhost:4200")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @Autowired
    private SectorMapper sectorMapper;

    @GetMapping({ "", "/" })
    ResponseEntity<SectorListReponseDto> listAll() {
        var sectorList = sectorService.listAll().stream()
                .map(sectorMapper::toSectorResponseDto)
                .toList();
        var sectors = new SectorListReponseDto(sectorList);

        return ResponseEntity.ok(sectors);
    }

    @GetMapping("/{id}")
    ResponseEntity<SectorResponseDto> find(@PathVariable Long id) {
        var sector = sectorService.find(id);
        var sectorDto = sectorMapper.toSectorResponseDto(sector);

        return ResponseEntity.ok(sectorDto);
    }

    @PostMapping({ "", "/" })
    ResponseEntity<SectorResponseDto> create(
            @RequestBody SectorRequestDto sectorRequestDto) {
        var sector = sectorMapper.toSector(sectorRequestDto);
        var createdSector = sectorService.create(sector);
        var createdSectorDto = sectorMapper.toSectorResponseDto(createdSector);

        return ResponseEntity
                .created(URI.create("/sector/" + createdSector.getId()))
                .body(createdSectorDto);
    }

    @PutMapping("/{id}")
    ResponseEntity<SectorResponseDto> update(
            @PathVariable Long id,
            @RequestBody SectorRequestDto sectorRequestDto) {
        var sector = sectorMapper.toSector(sectorRequestDto);
        var updatedSector = sectorService.update(id, sector);
        var updatedSectorDto = sectorMapper.toSectorResponseDto(updatedSector);

        return ResponseEntity.ok(updatedSectorDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> remove(@PathVariable Long id) {
        sectorService.remove(id);

        return ResponseEntity.noContent().build();
    }
}

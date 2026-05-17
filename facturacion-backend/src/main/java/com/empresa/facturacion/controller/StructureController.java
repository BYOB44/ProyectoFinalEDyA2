package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.StructureSummary;
import com.empresa.facturacion.service.StructureService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/structures")
public class StructureController {
    private final StructureService structureService;

    public StructureController(StructureService structureService) {
        this.structureService = structureService;
    }

    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public StructureSummary getSummary() {
        return structureService.getSummary();
    }
}

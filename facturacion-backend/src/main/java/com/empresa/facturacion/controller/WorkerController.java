package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.WorkerRequest;
import com.empresa.facturacion.dto.WorkerResponse;
import com.empresa.facturacion.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<WorkerResponse> findAll() {
        return workerService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public WorkerResponse create(@Valid @RequestBody WorkerRequest request) {
        return workerService.create(request);
    }
}

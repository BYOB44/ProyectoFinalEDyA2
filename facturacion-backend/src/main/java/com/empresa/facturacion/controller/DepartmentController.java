package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.DepartmentRequest;
import com.empresa.facturacion.dto.DepartmentResponse;
import com.empresa.facturacion.dto.TreeNodeResponse;
import com.empresa.facturacion.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<DepartmentResponse> findAll() {
        return departmentService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public DepartmentResponse create(@Valid @RequestBody DepartmentRequest request) {
        return departmentService.create(request);
    }

    @GetMapping("/tree")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<TreeNodeResponse> getTree() {
        return departmentService.getTree();
    }
}

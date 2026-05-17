package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.DashboardStats;
import com.empresa.facturacion.service.DashboardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public DashboardStats getStats() {
        return dashboardService.getStats();
    }
}

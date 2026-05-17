package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.InvoiceRequest;
import com.empresa.facturacion.dto.InvoiceResponse;
import com.empresa.facturacion.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<InvoiceResponse> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/pending")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<InvoiceResponse> findPending() {
        return invoiceService.findPending();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('WORKER')")
    public List<InvoiceResponse> findMyInvoices(@AuthenticationPrincipal UserDetails userDetails) {
        return invoiceService.findMyInvoices(userDetails.getUsername());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public InvoiceResponse create(@Valid @RequestBody InvoiceRequest request) {
        return invoiceService.create(request);
    }
}

package com.empresa.facturacion.controller;

import com.empresa.facturacion.dto.InvoiceResponse;
import com.empresa.facturacion.dto.PaymentResponse;
import com.empresa.facturacion.service.PaymentQueueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentQueueService paymentQueueService;

    public PaymentController(PaymentQueueService paymentQueueService) {
        this.paymentQueueService = paymentQueueService;
    }

    @GetMapping("/queue")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<InvoiceResponse> getQueue(@RequestParam(defaultValue = "fifo") String mode) {
        return paymentQueueService.getQueue(mode);
    }

    @GetMapping("/next")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public InvoiceResponse getNext(@RequestParam(defaultValue = "fifo") String mode) {
        return paymentQueueService.getNext(mode);
    }

    @PostMapping("/process")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public PaymentResponse processNext(@RequestParam(defaultValue = "fifo") String mode, @AuthenticationPrincipal UserDetails userDetails) {
        return paymentQueueService.processNext(mode, userDetails.getUsername());
    }

    @GetMapping("/history")
    @PreAuthorize("hasAnyRole('ADMIN','TREASURER')")
    public List<PaymentResponse> history() {
        return paymentQueueService.getHistoryAsStack();
    }
}

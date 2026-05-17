package com.empresa.facturacion.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {
    private Long id;
    private String invoiceCode;
    private String workerName;
    private BigDecimal amount;
    private LocalDateTime paidAt;
    private String processedBy;

    public PaymentResponse(Long id, String invoiceCode, String workerName, BigDecimal amount, LocalDateTime paidAt,
            String processedBy) {
        this.id = id;
        this.invoiceCode = invoiceCode;
        this.workerName = workerName;
        this.amount = amount;
        this.paidAt = paidAt;
        this.processedBy = processedBy;
    }

    public Long getId() {
        return id;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public String getWorkerName() {
        return workerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public String getProcessedBy() {
        return processedBy;
    }
}

package com.empresa.facturacion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceResponse {
    private Long id;
    private String code;
    private String description;
    private BigDecimal amount;
    private String type;
    private String priority;
    private String status;
    private LocalDate issuedAt;
    private Long workerId;
    private String workerName;

    public InvoiceResponse(Long id, String code, String description, BigDecimal amount, String type, String priority,
            String status, LocalDate issuedAt, Long workerId, String workerName) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.priority = priority;
        this.status = status;
        this.issuedAt = issuedAt;
        this.workerId = workerId;
        this.workerName = workerName;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getIssuedAt() {
        return issuedAt;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getWorkerName() {
        return workerName;
    }
}

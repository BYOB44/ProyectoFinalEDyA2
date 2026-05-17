package com.empresa.facturacion.dto;

import com.empresa.facturacion.model.InvoiceType;
import com.empresa.facturacion.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class InvoiceRequest {
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private InvoiceType type;
    @NotNull
    private Priority priority;
    @NotNull
    private Long workerId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }
}

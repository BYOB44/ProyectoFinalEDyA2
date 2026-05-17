package com.empresa.facturacion.dto;

import java.math.BigDecimal;

public class DashboardStats {
    private long departments;
    private long workers;
    private long pendingInvoices;
    private long paidInvoices;
    private BigDecimal pendingAmount;
    private BigDecimal paidAmount;

    public DashboardStats(long departments, long workers, long pendingInvoices, long paidInvoices, BigDecimal pendingAmount, BigDecimal paidAmount) {
        this.departments = departments;
        this.workers = workers;
        this.pendingInvoices = pendingInvoices;
        this.paidInvoices = paidInvoices;
        this.pendingAmount = pendingAmount;
        this.paidAmount = paidAmount;
    }

    public long getDepartments() { return departments; }
    public long getWorkers() { return workers; }
    public long getPendingInvoices() { return pendingInvoices; }
    public long getPaidInvoices() { return paidInvoices; }
    public BigDecimal getPendingAmount() { return pendingAmount; }
    public BigDecimal getPaidAmount() { return paidAmount; }
}

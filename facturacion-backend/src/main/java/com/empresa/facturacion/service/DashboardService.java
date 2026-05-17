package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.DashboardStats;
import com.empresa.facturacion.model.Invoice;
import com.empresa.facturacion.model.InvoiceStatus;
import com.empresa.facturacion.repository.DepartmentRepository;
import com.empresa.facturacion.repository.InvoiceRepository;
import com.empresa.facturacion.repository.WorkerRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class DashboardService {
    private final DepartmentRepository departmentRepository;
    private final WorkerRepository workerRepository;
    private final InvoiceRepository invoiceRepository;

    public DashboardService(DepartmentRepository departmentRepository, WorkerRepository workerRepository, InvoiceRepository invoiceRepository) {
        this.departmentRepository = departmentRepository;
        this.workerRepository = workerRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public DashboardStats getStats() {
        var invoices = invoiceRepository.findAll();
        long pending = invoices.stream().filter(i -> i.getStatus() == InvoiceStatus.PENDING).count();
        long paid = invoices.stream().filter(i -> i.getStatus() == InvoiceStatus.PAID).count();
        BigDecimal pendingAmount = invoices.stream()
                .filter(i -> i.getStatus() == InvoiceStatus.PENDING)
                .map(Invoice::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal paidAmount = invoices.stream()
                .filter(i -> i.getStatus() == InvoiceStatus.PAID)
                .map(Invoice::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DashboardStats(
                departmentRepository.count(),
                workerRepository.count(),
                pending,
                paid,
                pendingAmount,
                paidAmount
        );
    }
}

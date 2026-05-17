package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.*;
import com.empresa.facturacion.model.*;
import org.springframework.stereotype.Service;

@Service
public class MapperService {
    public DepartmentResponse toDepartmentResponse(Department department) {
        Long parentId = department.getParent() != null ? department.getParent().getId() : null;
        String parentName = department.getParent() != null ? department.getParent().getName() : null;
        return new DepartmentResponse(department.getId(), department.getName(), parentId, parentName);
    }

    public WorkerResponse toWorkerResponse(Worker worker) {
        return new WorkerResponse(
                worker.getId(),
                worker.getFullName(),
                worker.getPosition(),
                worker.getEmail(),
                worker.getDepartment().getId(),
                worker.getDepartment().getName()
        );
    }

    public InvoiceResponse toInvoiceResponse(Invoice invoice) {
        return new InvoiceResponse(
                invoice.getId(),
                invoice.getCode(),
                invoice.getDescription(),
                invoice.getAmount(),
                invoice.getType().name(),
                invoice.getPriority().name(),
                invoice.getStatus().name(),
                invoice.getIssuedAt(),
                invoice.getWorker().getId(),
                invoice.getWorker().getFullName()
        );
    }

    public PaymentResponse toPaymentResponse(Payment payment) {
        String processedBy = payment.getProcessedBy() != null ? payment.getProcessedBy().getUsername() : "Sistema";
        return new PaymentResponse(
                payment.getId(),
                payment.getInvoice().getCode(),
                payment.getInvoice().getWorker().getFullName(),
                payment.getAmount(),
                payment.getPaidAt(),
                processedBy
        );
    }
}

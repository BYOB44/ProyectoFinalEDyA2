package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.StructureSummary;
import com.empresa.facturacion.repository.InvoiceRepository;
import com.empresa.facturacion.structures.CompanyGraph;
import org.springframework.stereotype.Service;

@Service
public class StructureService {
    private final PaymentQueueService paymentQueueService;
    private final DepartmentService departmentService;

    public StructureService(PaymentQueueService paymentQueueService, DepartmentService departmentService, InvoiceRepository invoiceRepository) {
        this.paymentQueueService = paymentQueueService;
        this.departmentService = departmentService;
    }

    public StructureSummary getSummary() {
        CompanyGraph graph = CompanyGraph.defaultGraph();
        return new StructureSummary(
                paymentQueueService.getQueue("fifo"),
                paymentQueueService.getQueue("priority"),
                paymentQueueService.getHistoryAsStack(),
                departmentService.getTree(),
                graph.getAdjacencyList(),
                graph.shortestPath("Admin", "Pagos")
        );
    }
}

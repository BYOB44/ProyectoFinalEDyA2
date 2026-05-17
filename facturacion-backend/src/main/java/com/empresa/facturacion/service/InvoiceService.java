package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.InvoiceRequest;
import com.empresa.facturacion.dto.InvoiceResponse;
import com.empresa.facturacion.model.*;
import com.empresa.facturacion.repository.InvoiceRepository;
import com.empresa.facturacion.repository.UserAccountRepository;
import com.empresa.facturacion.repository.WorkerRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final WorkerRepository workerRepository;
    private final UserAccountRepository userAccountRepository;
    private final MapperService mapperService;

    public InvoiceService(InvoiceRepository invoiceRepository, WorkerRepository workerRepository, UserAccountRepository userAccountRepository, MapperService mapperService) {
        this.invoiceRepository = invoiceRepository;
        this.workerRepository = workerRepository;
        this.userAccountRepository = userAccountRepository;
        this.mapperService = mapperService;
    }

    public List<InvoiceResponse> findAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(mapperService::toInvoiceResponse)
                .toList();
    }

    public List<InvoiceResponse> findPending() {
        return invoiceRepository.findByStatusOrderByIssuedAtAscIdAsc(InvoiceStatus.PENDING)
                .stream()
                .map(mapperService::toInvoiceResponse)
                .toList();
    }

    public List<InvoiceResponse> findMyInvoices(String username) {
        UserAccount user = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (user.getWorker() == null) {
            return List.of();
        }
        return invoiceRepository.findByWorkerOrderByIssuedAtDescIdDesc(user.getWorker())
                .stream()
                .map(mapperService::toInvoiceResponse)
                .toList();
    }

    public InvoiceResponse create(InvoiceRequest request) {
        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        Invoice invoice = new Invoice(
                generateCode(),
                request.getDescription(),
                request.getAmount(),
                request.getType(),
                request.getPriority(),
                InvoiceStatus.PENDING,
                LocalDate.now(),
                worker
        );

        return mapperService.toInvoiceResponse(invoiceRepository.save(invoice));
    }

    private String generateCode() {
        return "FAC-" + System.currentTimeMillis();
    }
}

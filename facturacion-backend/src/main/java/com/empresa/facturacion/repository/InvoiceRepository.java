package com.empresa.facturacion.repository;

import com.empresa.facturacion.model.Invoice;
import com.empresa.facturacion.model.InvoiceStatus;
import com.empresa.facturacion.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByStatusOrderByIssuedAtAscIdAsc(InvoiceStatus status);
    List<Invoice> findByWorkerOrderByIssuedAtDescIdDesc(Worker worker);
}

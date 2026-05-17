package com.empresa.facturacion.repository;

import com.empresa.facturacion.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByOrderByPaidAtDesc();
}

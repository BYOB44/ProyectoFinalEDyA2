package com.empresa.facturacion.service;

import com.empresa.facturacion.dto.InvoiceResponse;
import com.empresa.facturacion.dto.PaymentResponse;
import com.empresa.facturacion.model.*;
import com.empresa.facturacion.repository.InvoiceRepository;
import com.empresa.facturacion.repository.PaymentRepository;
import com.empresa.facturacion.repository.UserAccountRepository;
import com.empresa.facturacion.structures.CustomQueue;
import com.empresa.facturacion.structures.CustomStack;
import com.empresa.facturacion.structures.PriorityPaymentHeap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentQueueService {
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final UserAccountRepository userAccountRepository;
    private final MapperService mapperService;

    public PaymentQueueService(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository, UserAccountRepository userAccountRepository, MapperService mapperService) {
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
        this.userAccountRepository = userAccountRepository;
        this.mapperService = mapperService;
    }

    public List<InvoiceResponse> getQueue(String mode) {
        List<Invoice> pending = invoiceRepository.findByStatusOrderByIssuedAtAscIdAsc(InvoiceStatus.PENDING);
        if ("priority".equalsIgnoreCase(mode)) {
            PriorityPaymentHeap heap = new PriorityPaymentHeap();
            pending.forEach(heap::insert);
            return heap.toOrderedList().stream().map(mapperService::toInvoiceResponse).toList();
        }

        CustomQueue<Invoice> queue = new CustomQueue<>();
        pending.forEach(queue::enqueue);
        return queue.toList().stream().map(mapperService::toInvoiceResponse).toList();
    }

    public InvoiceResponse getNext(String mode) {
        List<InvoiceResponse> queue = getQueue(mode);
        return queue.isEmpty() ? null : queue.get(0);
    }

    @Transactional
    public PaymentResponse processNext(String mode, String username) {
        List<Invoice> pending = invoiceRepository.findByStatusOrderByIssuedAtAscIdAsc(InvoiceStatus.PENDING);
        if (pending.isEmpty()) {
            throw new RuntimeException("No hay facturas pendientes para pagar");
        }

        Invoice selected;
        if ("priority".equalsIgnoreCase(mode)) {
            PriorityPaymentHeap heap = new PriorityPaymentHeap();
            pending.forEach(heap::insert);
            selected = heap.extractMax();
        } else {
            CustomQueue<Invoice> queue = new CustomQueue<>();
            pending.forEach(queue::enqueue);
            selected = queue.dequeue();
        }

        UserAccount user = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        selected.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(selected);

        Payment payment = new Payment(selected, selected.getAmount(), LocalDateTime.now(), user);
        return mapperService.toPaymentResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> getHistoryAsStack() {
        CustomStack<Payment> stack = new CustomStack<>();
        List<Payment> payments = paymentRepository.findAllByOrderByPaidAtDesc();
        for (int i = payments.size() - 1; i >= 0; i--) {
            stack.push(payments.get(i));
        }
        return stack.toList().stream().map(mapperService::toPaymentResponse).toList();
    }
}

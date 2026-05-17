package com.empresa.facturacion.dto;

import java.util.List;
import java.util.Map;

public class StructureSummary {
    private List<InvoiceResponse> fifoQueue;
    private List<InvoiceResponse> priorityHeapOrder;
    private List<PaymentResponse> paymentStack;
    private List<TreeNodeResponse> departmentTree;
    private Map<String, List<String>> companyGraph;
    private List<String> examplePath;

    public StructureSummary(List<InvoiceResponse> fifoQueue, List<InvoiceResponse> priorityHeapOrder,
            List<PaymentResponse> paymentStack, List<TreeNodeResponse> departmentTree,
            Map<String, List<String>> companyGraph, List<String> examplePath) {
        this.fifoQueue = fifoQueue;
        this.priorityHeapOrder = priorityHeapOrder;
        this.paymentStack = paymentStack;
        this.departmentTree = departmentTree;
        this.companyGraph = companyGraph;
        this.examplePath = examplePath;
    }

    public List<InvoiceResponse> getFifoQueue() {
        return fifoQueue;
    }

    public List<InvoiceResponse> getPriorityHeapOrder() {
        return priorityHeapOrder;
    }

    public List<PaymentResponse> getPaymentStack() {
        return paymentStack;
    }

    public List<TreeNodeResponse> getDepartmentTree() {
        return departmentTree;
    }

    public Map<String, List<String>> getCompanyGraph() {
        return companyGraph;
    }

    public List<String> getExamplePath() {
        return examplePath;
    }
}

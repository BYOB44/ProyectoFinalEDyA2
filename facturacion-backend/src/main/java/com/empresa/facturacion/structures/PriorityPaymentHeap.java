package com.empresa.facturacion.structures;

import com.empresa.facturacion.model.Invoice;
import java.util.ArrayList;
import java.util.List;

public class PriorityPaymentHeap {
    private final List<Invoice> heap = new ArrayList<>();

    public void insert(Invoice invoice) {
        heap.add(invoice);
        heapifyUp(heap.size() - 1);
    }

    public Invoice peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    public Invoice extractMax() {
        if (heap.isEmpty()) {
            return null;
        }
        Invoice root = heap.get(0);
        Invoice last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return root;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public List<Invoice> toOrderedList() {
        PriorityPaymentHeap copy = new PriorityPaymentHeap();
        for (Invoice invoice : heap) {
            copy.insert(invoice);
        }
        List<Invoice> ordered = new ArrayList<>();
        while (!copy.isEmpty()) {
            ordered.add(copy.extractMax());
        }
        return ordered;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (hasHigherPriority(heap.get(index), heap.get(parent))) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int left;
        int right;
        int highest;

        while (true) {
            left = index * 2 + 1;
            right = index * 2 + 2;
            highest = index;

            if (left < heap.size() && hasHigherPriority(heap.get(left), heap.get(highest))) {
                highest = left;
            }
            if (right < heap.size() && hasHigherPriority(heap.get(right), heap.get(highest))) {
                highest = right;
            }
            if (highest == index) {
                break;
            }
            swap(index, highest);
            index = highest;
        }
    }

    private boolean hasHigherPriority(Invoice a, Invoice b) {
        int priorityCompare = Integer.compare(a.getPriority().getLevel(), b.getPriority().getLevel());
        if (priorityCompare != 0) {
            return priorityCompare > 0;
        }
        int dateCompare = a.getIssuedAt().compareTo(b.getIssuedAt());
        if (dateCompare != 0) {
            return dateCompare < 0;
        }
        return a.getId() < b.getId();
    }

    private void swap(int a, int b) {
        Invoice temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }
}

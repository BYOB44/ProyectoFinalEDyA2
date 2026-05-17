package com.empresa.facturacion.structures;

import java.util.ArrayList;
import java.util.List;

public class CustomStack<T> {
    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> top;
    private int size;

    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.next = top;
        top = node;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    public T peek() {
        return isEmpty() ? null : top.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> values = new ArrayList<>();
        Node<T> current = top;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }
}

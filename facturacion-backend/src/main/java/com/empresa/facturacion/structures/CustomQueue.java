package com.empresa.facturacion.structures;

import java.util.ArrayList;
import java.util.List;

public class CustomQueue<T> {
    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;

    public void enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T value = front.value;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return value;
    }

    public T peek() {
        return isEmpty() ? null : front.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public List<T> toList() {
        List<T> values = new ArrayList<>();
        Node<T> current = front;
        while (current != null) {
            values.add(current.value);
            current = current.next;
        }
        return values;
    }
}

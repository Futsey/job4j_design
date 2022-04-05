package ru.job4j.list.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> firstEl = head;
        head = firstEl.next;
        firstEl.next = null;
        return firstEl.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        return "ForwardLinked{"
                + "head=" + head
                + '}';
    }

    public static void main(String[] args) {
        ForwardLinked list = new ForwardLinked();
        list.add(12);
        list.add(3);
        list.add(52);
        list.add(6);
        System.out.println(list);
        list.deleteFirst();
        System.out.println(list);
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{"
                    + "value=" + value
                    + ", next=" + next
                    + '}';
        }
    }
}

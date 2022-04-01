package ru.job4j.list.linkedlist;

import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    int modCount;
    Node<E> head;
    Node<E> tail;

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.value = value;
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, modCount);
        Node<E> currentEl = head;
        for (int i = 0; i < index; i++) {
            currentEl = currentEl.getNext();
        }
        return currentEl.getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> currentEl = head;

            @Override
            public boolean hasNext() {
                return currentEl != null;
            }

            @Override
            public E next() {
                E el = currentEl.getValue();
                currentEl = currentEl.next;
                return el;
            }
        };
    }

    @Override
    public String toString() {
        return "SimpleLinkedList{"
                + "modCount=" + modCount
                + ", head=" + head
                + '}';
    }

    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        System.out.println(list.get(0));
        list.add(2);
        System.out.println(list.get(1));
        System.out.println(list);
        list.add(3);
        System.out.println(list);
        System.out.println(list.get(2));
        list.add(4);
        System.out.println(list.get(3));
        list.add(5);
        System.out.println(list.get(3));
        list.add(6);
        System.out.println(list.get(4));
    }
}

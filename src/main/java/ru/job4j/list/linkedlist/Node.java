package ru.job4j.list.linkedlist;

public class Node<E> {

    E value;
    Node<E> next;

    public Node(E value) {
        this.value = value;
        next = null;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
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

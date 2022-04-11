package ru.job4j.list.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс является собственной реализацией (односвязный список)
 * @see java.util.LinkedList
 * реализует собственную версию
 * @see java.util.List
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class ForwardLinked<T> implements Iterable<T> {

    /**
     * поле head является первым элементом в множестве
     */
    private Node<T> head;

    /**
     * метод добавляет новый элемент в множество, производя при этом валидацию первого элемента на null-значение
     * в случае, если элемент имеет null-значение, то ссылка будет назначена первым (head-элементом)
     * если элемент не имеет null-значение, элементу присваиваются ссылка на следующий элемент
     */
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

    /**
     * метод добавляет новый первый элемент в множество
     */
    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * метод удаляет самый первый элемент в множестве, производя при этом валидацию первого элемента на null-значение
     * в случае, если элемент имеет null-значение, то выбрасывается исключение
     * @see NoSuchElementException
     * если элемент не имеет null-значение, то элемент:
     * присваивает следующему элементу ссылку на первый элемент (head-элемент)
     * и зануляет собственную ссылку на следующий элемент
     * @return старое значение
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> firstEl = head;
        T newEl = head.value;
        head = head.next;
        firstEl.value = null;
        firstEl.next = null;
        return newEl;
    }

    /**
     * метод переворачивает односвязный список, индексируя элементы от последнего к первому
     * @return булево значение
     */
    public boolean revert() {
        boolean rsl = false;
        if (head != null && head.next != null) {
            Node<T> currentNode = head.next;
            head.next = null;
            while (currentNode != null) {
                Node<T> nextNode = currentNode.next;
                currentNode.next = head;
                head = currentNode;
                currentNode = nextNode;
                rsl = true;
            }
        }
        return rsl;
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

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

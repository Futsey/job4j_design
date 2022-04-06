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
        list.addFirst(11);
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

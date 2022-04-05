package ru.job4j.list.linkedlist;

import java.util.Iterator;
import java.util.Objects;

/**
 * Класс является собственной реализацией (двусвязный список)
 * @see java.util.LinkedList
 * реализует собственную версию
 * @see java.util.List
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleLinkedList<E> implements List<E> {
    /**
     * поле modCount является счетчиком по добавлению\удалению элементов
     * поле head является первым элементом в множестве
     * поле tail является последним элементом в множестве
     */
    int modCount;
    Node<E> head;
    Node<E> tail;

    /**
     * метод проверяет, является ли самый первый элемент null-значением
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * метод добавляет новый элемент в множество, производя при этом валидацию первого элемента на null-значение
     * {@link #isEmpty()}
     * в случае, если элемент имеет null-значение, то первая ссылка будет назначена первым (head-элементом)
     * если элемент не имеет null-значение, элементу присваиваются ссылки на предыдущий и следующий элементы
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        modCount++;
    }

    /**
     * метод получает искомый элемент по индексу, предварительно проверив диапозон индексов
     * @return значение по индексу
     */
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

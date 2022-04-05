package ru.job4j.list.stack;

import ru.job4j.list.linkedlist.ForwardLinked;
import ru.job4j.list.linkedlist.Node;

import java.util.Comparator;

/**
 * Класс является собственной реализацией контейнера
 * @see java.util.Stack
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleStack<T> {

    /**
     * все данные содержим в односвязном списке собственной реализации
     * @see ForwardLinked
     */
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * метод удаляет первый элемент в множестве
     * @return старое значение (значение удаленного элемента)
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * метод добавляет элемент в самое начало множества
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean hasNext() {
        return linked.iterator().hasNext();
    }
}

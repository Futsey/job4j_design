package ru.job4j.list.stack;

import ru.job4j.list.linkedlist.ForwardLinked;

/**
 * Класс является собственной реализацией контейнера
 * @see java.util.Stack
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleStack<T> {

    /**
     * Все данные содержим в односвязном списке собственной реализации
     * @see ForwardLinked
     */
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод удаляет первый элемент в множестве
     * @return старое значение (значение удаленного элемента)
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод добавляет элемент в самое начало множества
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}

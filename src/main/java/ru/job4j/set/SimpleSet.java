package ru.job4j.set;

import ru.job4j.list.arraylist.SimpleArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс является собственной реализацией
 * @see java.util.Set
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {

    /**
     * данные храним в ArrayList с универсальной параметризацией <T>
     */
    private SimpleArrayList<T> set = new SimpleArrayList<>(5);


    /**
     * Метод принимает новое значение
     * @param value
     * и, в случае отсутствия элемента в множестве, добавляет новый элемент
     * @return старое значение
     */
    @Override
    public boolean add(T value) {
        boolean rsl = !contains(value);
        if (rsl) {
            set.add(value);
        }
        return rsl;
    }

    /**
     * Метод принимает значение
     * @param value
     * проверяет множество на наличие элемента
     * @return true\false (нашел\не нашел)
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                rsl = true;
            }
        }
        return rsl;
    }

    /**
     * Метод печатает в консоль значение
     * @param value
     * если значение находится в множестве
     * @return значение или исключение
     * @see NoSuchElementException если элемент отсутствует в множестве
     */
    public T show(T value) {
        if (contains(value)) {
            System.out.println(value);
            return value;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

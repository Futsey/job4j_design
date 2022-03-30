package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс является собственной реализацией
 * @see java.util.ArrayList
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleArrayList<T> implements List<T> {

    /**
     * данные храним в массиве с универсальной параметризацией T[]
     * поле size хранит в себе количество элементов
     * поле modCount является счетчиком по добавлению\удалению элементов посредством методов
     * с передаваемым индексом в параметрах:
     * {@link #set(int, Object)}
     * {@link #remove(int)}
     * {@link #get(int)} )}
     */
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод принимает массив
     * @param container
     * удваивает Capacity
     * @return массив с увеличенным Capacity
     */
    private T[] grow(T[] container) {
        int grow = container.length * 2;
        T[] newContainer = (T[]) new Object[grow];
        for (int i = 0; i < container.length; i++) {
            newContainer[i] = container[i];
        }
        return newContainer;
    }

    /**
     * Метод пдобавляет новый элемент в массив, прежде проверив достаточно-ли места (Capacity)
     * Если места (Capacity) недостаточно, то с помощью метода
     * {@link #grow(Object[])}
     * производим расширение массива, дополнительно обновляя счетчики size и modCount
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow(container);
            modCount++;
        } else {
            container[size] = value;
            size++;
            modCount++;
        }
    }

    /**
     * Метод меняет значение элемента по индексу, прежде проведя валидацию диапозона индексов
     * @return старое значение или null
     */
    @Override
    public T set(int index, T newValue) {
        T tmp = null;
        if (Objects.checkIndex(index, container.length) > 0) {
            tmp = container[index];
            container[index] = newValue;
        }
        return tmp;
    }

    /**
     * Метод удаляет элемент по индексу, прежде проведя валидацию диапозона индексов
     * и сдвинув весь массив влево, зануляя последнее значение
     * @return старое значение или null
     */
    @Override
    public T remove(int index) {
        T tmp = null;
        if (Objects.checkIndex(index, container.length) > 0) {
            tmp = container[index];
            for (int i = index; i < container.length; i++) {
                if (i != container.length - 1) {
                    container[i] = container[i + 1];
                } else {
                    container[i] = null;
                }
            }
            size--;
            modCount++;
        }
        return tmp;
    }

    /**
     * Метод получает элемент по индексу, прежде проведя валидацию диапозона индексов
     * @return старое значение
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return  (T) container[index];
    }

    /**
     * Метод получает количество элементов массива (не путать с Capacity)
     * @return числовое выражение количества элементов
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pointer++];
            }
        };
    }
}

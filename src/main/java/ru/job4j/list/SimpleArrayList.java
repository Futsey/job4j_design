package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private int grow(T[] container) {
        return container.length * 2;
    }

    private boolean check(int index) {
        return Objects.checkIndex(index, container.length) > 0;
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            int grow = grow(container);
            container = (T[]) new Object[grow];
            container[grow - size] = value;
            modCount++;
        } else {
            container[size] = value;
            size++;
            modCount++;
        }
    }

    @Override
    public T set(int index, T newValue) {
        check(index);
        T tmp = container[index];
        container[index] = newValue;
        return tmp;
    }

    @Override
    public T remove(int index) {
        T tmp = null;
        if (check(index)) {
            tmp = container[index];
            for (int i = index; i < container.length; i++) {
                if (i != container.length - 1) {
                    container[i] = container[i + 1];
                }
            }
        }
        modCount++;
        size--;
        return tmp;
    }

    @Override
    public T get(int index) {
        return container[index];
    }

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
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[pointer++];
            }
        };
    }
}

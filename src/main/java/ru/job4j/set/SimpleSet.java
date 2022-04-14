package ru.job4j.set;

import ru.job4j.list.arraylist.SimpleArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(5);

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

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

    public T show(T value) {
        if (contains(value)) {
            System.out.println(value);
            return value;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

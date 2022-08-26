package ru.job4j.foodstore;

import java.util.List;

public interface Repository {

    default <E> void save(List<E> list, E product) {
    }
}

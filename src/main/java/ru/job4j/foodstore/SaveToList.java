package ru.job4j.foodstore;

import java.util.List;

public class SaveToList implements Repository {

    @Override
    public <E> void save(List<E> list, E product) {
        list.add(product);
    }

    public <E> E getProduct(List<E> list, int id) {
        return list.get(id);
    }
}

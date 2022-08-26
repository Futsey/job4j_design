package ru.job4j.foodstore;

public interface Store {

    void add(Food product);
    Food getProduct(int id);
}

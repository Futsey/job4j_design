package ru.job4j.foodstore;

public interface Store {

    boolean add(Food product);
    <E> E getAllProducts();
    <E> E getProduct(int id);

}

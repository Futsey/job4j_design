package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private Repository saveToList = new SaveToList();
    private List<Food> warehouseList = new ArrayList<>();

    @Override
    public void add(Food product) {
        saveToList.save(warehouseList, product);
        System.out.println(product + " added to warehouseList");
    }

    public Food getProduct(int id) {
        return ((SaveToList) saveToList).getProduct(warehouseList, id);
    }
}

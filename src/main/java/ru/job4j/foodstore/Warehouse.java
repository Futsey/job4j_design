package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private QualityControl qc = new QualityControl();
    private List<Food> warehouseList = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (product != null) {
            warehouseList.add(product);
            rsl = true;
        }
        return rsl;
    }

    public Food getProduct(int id) {
        return warehouseList.get(id);
    }

    @Override
    public Food getAllProducts() {
        List<Food> copyProductList = List.copyOf(warehouseList);
        return (Food) copyProductList;
    }
}

package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private QualityControl qc = new QualityControl();
    private List<Food> trashList = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (product != null) {
            trashList.add(product);
            rsl = true;
        }
        return rsl;
    }

    public Food getProduct(int id) {
        return trashList.get(id);
    }

    @Override
    public Food getAllProducts() {
        List<Food> copyProductList = List.copyOf(trashList);
        return (Food) copyProductList;
    }
}

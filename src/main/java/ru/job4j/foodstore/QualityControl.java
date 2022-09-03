package ru.job4j.foodstore;

import java.util.List;

public class QualityControl {

    private final List<Store> storages;

    public QualityControl(List<Store> storages) {
        this.storages = storages;
    }

    public void addFood(Food food) {
        for (Store stores : storages) {
            stores.add(food);
        }
    }
}

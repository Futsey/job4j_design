package ru.job4j.foodstore;

import java.util.List;

public class QualityControl {

    private final List<Store> storages;

    public QualityControl(List<Store> stores) {
        this.storages = stores;
    }

    public void addFood(Food food) {
        for (Store store : storages) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }
}

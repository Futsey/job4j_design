package ru.job4j.solid.lsp.foodstore;

import java.util.ArrayList;
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

    public void resort() {
        List<Food> productList = new ArrayList<>();
        for (Store store : storages) {
            productList.addAll(store.getAllProducts());
        }
        for (Food food : productList) {
            addFood(food);
        }
    }
}

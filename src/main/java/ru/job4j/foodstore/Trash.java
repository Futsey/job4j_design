package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

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

    @Override
    public boolean accept(Food product) {
        boolean rsl = false;
        double percent = getTimeDiffInPercent(product);
        if (percent >= StoreModifires.ROTTEN) {
            rsl = true;
        }
        return rsl;
    }

    public Food getProduct(int id) {
        return trashList.get(id);
    }

    @Override
    public ArrayList<Food> getAllProducts() {
        return new ArrayList<Food>(trashList);
    }
}

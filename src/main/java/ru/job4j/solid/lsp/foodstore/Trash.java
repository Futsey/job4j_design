package ru.job4j.solid.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> trashList = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (product != null && accept(product)) {
            trashList.add(product);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void clear() {
        trashList.clear();
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

    @Override
    public ArrayList<Food> getAllProducts() {
        return new ArrayList<Food>(trashList);
    }
}

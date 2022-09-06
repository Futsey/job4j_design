package ru.job4j.solid.lsp.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> warehouseList = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (product != null && accept(product)) {
            warehouseList.add(product);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public void clear() {
        warehouseList.clear();
    }

    @Override
    public boolean accept(Food product) {
        boolean rsl = false;
        double percent = getTimeDiffInPercent(product);
        if (percent < StoreModifires.WAREHOUSEFRESHNESS) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public ArrayList<Food> getAllProducts() {
        return new ArrayList<Food>(warehouseList);
    }
}

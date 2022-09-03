package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> productList = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        double percent = getTimeDiffInPercent(product);
        if (product != null && accept(product)) {
            /*
            if (percent >= StoreModifires.SHOPFRESHNESS && percent < StoreModifires.ROTTEN) {
                addDiscount(product);
            }
             */
            productList.add(product);
            rsl = true;
        }
        return rsl;
    }

    public boolean accept(Food product) {
        boolean rsl = false;
        double percent = getTimeDiffInPercent(product);
        if (percent >= StoreModifires.WAREHOUSEFRESHNESS && percent <= StoreModifires.SHOPFRESHNESS) {
            rsl = true;
        }

        return rsl;
    }

    public Food addDiscount(Food food) {
        food.setDiscount(StoreModifires.DISCOUNT);
        food.setPrice(food.getPrice() - ((food.getPrice() / 100) * food.getDiscount()));
        return food;
    }

    public Food getProduct(int id) {
        return productList.get(id);
    }

    @Override
    public ArrayList<Food> getAllProducts() {
        return new ArrayList<Food>(productList);
    }
}

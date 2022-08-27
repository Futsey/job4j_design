package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    public static final int DISCOUNT = 15;
    private QualityControl qc = new QualityControl();
    private List<Food> productList = new ArrayList<>();

    @Override
    public boolean add(Food product) {
        boolean rsl = false;
        if (product != null) {
            productList.add(product);
            rsl = true;
        }
        return rsl;
    }

    public Food addDiscount(Food food) {
        food.setDiscount(DISCOUNT);
        food.setPrice(food.getPrice() - ((food.getPrice() / 100) * food.getDiscount()));
        return food;
    }

    public Food getProduct(int id) {
        return productList.get(id);
    }

    @Override
    public Food getAllProducts() {
        List<Food> copyProductList = List.copyOf(productList);
        return (Food) copyProductList;
    }
}

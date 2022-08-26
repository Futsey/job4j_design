package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    public static final int DISCOUNT = 15;
    private Repository saveToList = new SaveToList();
    private List<Food> productList = new ArrayList<>();

    @Override
    public void add(Food product) {
        saveToList.save(productList, product);
        System.out.println("List size = " + productList.size());
        System.out.println(product + " added to productList");
    }

    public Food addDiscount(Food food) {
        food.setDiscount(DISCOUNT);
        System.out.println("Product discount ".concat(food.toString()).concat(" is: ") + DISCOUNT + " %");
        return new Food(food.getName(), food.getCreateDate(), food.getExpiryDate(), food.getPrice(), food.getDiscount());
    }

    public Food getProduct(int id) {
        return ((SaveToList) saveToList).getProduct(productList, id);
    }
}

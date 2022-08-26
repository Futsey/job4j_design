package ru.job4j.foodstore;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private Repository saveToList = new SaveToList();
    private List<Food> trashList = new ArrayList<>();

    @Override
    public void add(Food product) {
        saveToList.save(trashList, product);
        System.out.println(product + " added to trashList");
    }

    public Food getProduct(int id) {
        return ((SaveToList) saveToList).getProduct(trashList, id);
    }
}

package ru.job4j.solid.lsp.foodstore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public interface Store {

    default double getTimeDiffInPercent(Food food) {
        double percent;
        LocalDateTime now = LocalDateTime.now();
        double daysExp = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double daysLeft = DAYS.between(now, food.getExpiryDate());
        isDaysLeft(daysLeft);
        percent = 100 - (100 * (daysLeft / daysExp));
        return percent;
    }

    default boolean isDaysLeft(double daysLeft) {
        boolean rsl = false;
        if (daysLeft > 0) {
            rsl = true;
        } else {
            System.out.println("Expiration doesn`t valid. Check days left: " + daysLeft);
        }
        return rsl;
    }

    boolean accept(Food product);
    boolean add(Food product);
    void clear();
    <E> ArrayList<Food> getAllProducts();
}

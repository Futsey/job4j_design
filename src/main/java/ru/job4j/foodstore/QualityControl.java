package ru.job4j.foodstore;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

public class QualityControl {

    private static double percent = 0D;
    public static final int SENDTOWAREHOUSE = 25;
    public static final int SENDTOSHOP = 75;
    public static final int EXPIRED = 100;

    double getTimeDiffInPercent(Food food) {
        LocalDateTime now = LocalDateTime.now();
        double daysExp = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double daysLeft = DAYS.between(now, food.getExpiryDate());
        isDaysLeft(daysLeft);
        percent = 100 - (100 * (daysLeft / daysExp));
        return percent;
    }

    boolean isDaysLeft(double daysLeft) {
        boolean rsl = false;
        if (daysLeft > 0) {
            rsl = true;
        } else {
            System.out.println("Expiration doesn`t valid. Check days left: " + daysLeft);
        }
        return rsl;
    }

    void storeDistributor(double percent, Food food) {
        if (percent < SENDTOWAREHOUSE) {
            Store store = new Warehouse();
            store.add(food);
        }
        if (percent >= SENDTOWAREHOUSE && percent <= SENDTOSHOP) {
            Store store = new Shop();
            store.add(food);
        }
        if (percent >= SENDTOSHOP && percent < EXPIRED) {
            Store store = new Shop();
            ((Shop) store).addDiscount(food);
            store.add(food);
        }
        if (percent >= EXPIRED) {
            Store store = new Trash();
            store.add(food);
        }
    }
}

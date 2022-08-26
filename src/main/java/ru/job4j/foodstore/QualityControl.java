package ru.job4j.foodstore;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class QualityControl {

    private static double percent = 0D;
    public static final int SENDTOWAREHOUSE = 25;
    public static final int SENDTOSHOP = 75;
    public static final int EXPIRED = 100;

    double getTimeDiffInPercent(LocalDateTime createDate, LocalDateTime expDate) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("currentTimeMillis() = "
                + convertToDate(System.currentTimeMillis() / 1000L - 12 * 3600));
        double daysExp = DAYS.between(createDate, expDate);
        double daysLeft = DAYS.between(now, expDate);
        System.out.println("Days before expiration from create date: " + daysExp + " days");
        isDaysLeft(daysLeft);
        System.out.println("Days before expiration from now: " + daysLeft + " days");
        percent = 100 * (daysLeft / daysExp);
        System.out.println("The product is damaged by: " + percent + " %");
        return percent;
    }

    String convertToDate(long unixSeconds) {
        Date date = new java.util.Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
        return formattedDate;
    }

    boolean isDaysLeft(double daysLeft) {
        boolean rsl = false;
        if (daysLeft > 0) {
            rsl = true;
        } else {
            throw new NullPointerException("Expiration doesn`t valid. Check days left: " + daysLeft);
        }
        return rsl;
    }

    void storeDistributor(double percent, Food food) {
        if (percent < SENDTOWAREHOUSE) {
            Store store = new Warehouse();
            store.add(food);
            System.out.println("SENDTOWAREHOUSE");
        }
        if (percent >= SENDTOWAREHOUSE && percent <= SENDTOSHOP) {
            Store store = new Shop();
            store.add(food);
            System.out.println("SENDTOSHOP");
        }
        if (percent >= SENDTOSHOP && percent < EXPIRED) {
            Store store = new Shop();
            ((Shop) store).addDiscount(food);
            store.add(food);
            System.out.println("SENDTOSHOPWITHDISCOUNT");
        }
        if (percent >= EXPIRED) {
            Store store = new Trash();
            store.add(food);
            System.out.println("TRASH");
        }
    }
}

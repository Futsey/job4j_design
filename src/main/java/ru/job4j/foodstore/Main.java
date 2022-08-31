package ru.job4j.foodstore;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        LocalDateTime createDate = LocalDateTime.now();
        LocalDateTime testDate = createDate.minusDays(1);
        LocalDateTime expDate = createDate.plusDays(4);
        Food food = new Meat("Pork", testDate, expDate, 590D, 0);
        Food fruit = new Meat("Fruit", testDate, expDate, 300D, 0);
        System.out.println(food);
        QualityControl control = new QualityControl();
        control.getTimeDiffInPercent(food);
        System.out.println("=============");
        control.storeDistributor(control.getTimeDiffInPercent(food), food);
        control.storeDistributor(control.getTimeDiffInPercent(fruit), fruit);
    }
}
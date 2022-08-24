package ru.job4j.foodstore;

import java.time.LocalDateTime;

public class Fruit extends Food {

    public Fruit(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}

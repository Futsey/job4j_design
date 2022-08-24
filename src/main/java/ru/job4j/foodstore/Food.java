package ru.job4j.foodstore;

import java.time.LocalDateTime;

public class Food {

    private String name;
    private LocalDateTime createDate;
    private LocalDateTime expiryDate;
    private double price;
    private int discount;

    public Food(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate
                + ", expiryDate=" + expiryDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}

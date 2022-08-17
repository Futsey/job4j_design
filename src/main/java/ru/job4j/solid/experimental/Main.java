package ru.job4j.solid.experimental;

public class Main {

    public static void main(String[] args) {
        IIngridients potato = new Potato();
        IIngridients carrot = new Carrot();
        IIngridients porkFat = new PorkFat();
        Cook soup = new Borsch(potato, carrot, porkFat);
        soup.cook();
    }
}

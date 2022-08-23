package ru.job4j.solid.experimental;

public class Main {

    public static void main(String[] args) throws Exception {
        IIngridients potato = new Potato();
        IIngridients carrot = new Carrot(33);
        IIngridients porkFat = new PorkFat();
        Cook soup = new Borsch(potato, carrot, porkFat);
        soup.cook();

        IIngridients wCarrot = new WhiteCarrot(2);
        Cook soupForBoyare = new Borsch(potato, wCarrot, porkFat);
        soupForBoyare.cook();
    }
}

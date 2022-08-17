package ru.job4j.solid.experimental;

import java.util.Objects;

public class Borsch implements Cook {

    /**Абстрактные поля класса помогают нам создавать гибкий суповой набор
     *
     */
    IIngridients potato;
    IIngridients carrot;
    IIngridients porkFat;

    public Borsch(IIngridients potato, IIngridients carrot, IIngridients porkFat) {
        this.potato = potato;
        this.carrot = carrot;
        this.porkFat = porkFat;
    }

    /** И вот тут наши абстрактные поля с абстрактным методом выступают во всей красе, согласно принципам SOLID
     * борщевой набор готов! По этому же принципу можем жарить картошку, тушить тефтельки и печь торт...
     */
    @Override
    public void cook() {
        System.out.println("Borsch{}: cook(): Готовим борщ из: "
                .concat(String.valueOf(new Borsch(potato, carrot, porkFat))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Borsch borsch = (Borsch) o;
        return Objects.equals(potato, borsch.potato) && Objects.equals(carrot, borsch.carrot) && Objects.equals(porkFat, borsch.porkFat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potato, carrot, porkFat);
    }

    @Override
    public String toString() {
        return "Borsch{"
                + "potato=" + potato
                + ", carrot=" + carrot
                + ", porkFat=" + porkFat
                + '}';
    }
}

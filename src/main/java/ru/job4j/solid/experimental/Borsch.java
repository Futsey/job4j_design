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

    /** Третьим нарушением принципа ISP будет отсутствие реализации методов интерфейса в конкретном классе.
     * Мы же не можем пожарить суп (хотя иногда и получается...). И если два метода cookMeat() и cookVegetables()
     * как-то можно за уши подтянуть в реализации (борщ с мясом, борщ только с овощами),
     * то об искусстве жарить борщ среднестатистическая хозяйка вряд-ли слышала
     */
    @Override
    public void cookMeat() {

    }

    @Override
    public void cookVegetables() {

    }

    @Override
    public void fry() {

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

package ru.job4j.solid.experimental;

public class Carrot implements IVegetables {

    /** На примере морковки рассмотрим нарушение принципа подстановки Liskov
     */
    private int weight;

    public Carrot(int weight) {
        this.weight = weight;
    }

    @Override
    public IIngridients addIngridient() {
        return new Carrot(weight);
    }

    @Override
    public IIngridients getIngridient() {
        return null;
    }

    @Override
    public String toString() {
        return "Carrot{}";
    }
}

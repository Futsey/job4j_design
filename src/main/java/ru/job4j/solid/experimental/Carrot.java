package ru.job4j.solid.experimental;

public class Carrot implements IVegetables {

    @Override
    public IIngridients addIngridient() {
        return new Carrot();
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

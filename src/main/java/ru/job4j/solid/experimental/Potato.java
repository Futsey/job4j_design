package ru.job4j.solid.experimental;

public class Potato implements IVegetables {

    @Override
    public IIngridients addIngridient() {
        return new Potato();
    }

    @Override
    public IIngridients getIngridient() {
        return null;
    }

    @Override
    public String toString() {
        return "Potato{}";
    }
}

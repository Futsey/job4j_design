package ru.job4j.solid.experimental;

public class Ingridients implements IIngridients {

    IIngridients ingridients;

    public Ingridients(IIngridients ingridients) {
        this.ingridients = ingridients;
    }

    @Override
    public IIngridients addIngridient() {
        return new Ingridients(ingridients);
    }

    @Override
    public IIngridients getIngridient() {
        return null;
    }
}

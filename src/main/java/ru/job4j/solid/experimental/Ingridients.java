package ru.job4j.solid.experimental;

public class Ingridients implements IIngridients {

    IIngridients ingridients;

    public Ingridients(IIngridients ingridients) {
        this.ingridients = ingridients;
    }

    /** Нарушим и сдесь принцип DIP, вернув в методе не абстрактный ингридент из конструктора,
     * а вполне себе конкретный Potato potato.
     * @return new Potato()
     */
    @Override
    public IIngridients addIngridient() {
        return new Potato();
    }

    @Override
    public IIngridients getIngridient() {
        return null;
    }
}

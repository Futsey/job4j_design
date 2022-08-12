package ru.job4j.solid.correctimpl;

public class Truekitchen implements Cook, Wash {

    @Override
    public void cookFish() {

    }

    @Override
    public void cookBorsch() {

    }

    /** Нарушим принцип SRP и создадим внутри метода экземпляр класса Картофель, нам ведь надо что-то жарить.
     * А по принципу SRP мы должны бы в аргументе метода этот картофель передать.
     */
    @Override
    public void fryPotato() {
        Potato potato = new Potato();
    }

    /** И здесь нарушим принцип SRP, использовав Singleton. Мало того, что он делает почти невозможным
     * тестирование с его участием, программа становится зависимой от конкретного состояния синглтона
     * в момент использования, а принцип SRP говорит об обратном.
     *
     */
    @Override
    public void omelette() {
        System.out.println("Готовим омлет" + SingletonPotato.GOLDEGG);
    }

    @Override
    public void washDishes() {

    }

    @Override
    public void washGasStove() {

    }
}

package ru.job4j.solid.experimental;

public interface IVegetables extends IIngridients {

    /**
     * Теперь, в соответствии с принципом OCP мы получили возможность добавлять любые овощи в любое блюдо,
     * не только в суп и все благодаря абстрактному составному классу Ингридиентов.
     * @see ru.job4j.solid.experimental.IIngridients
     * Например, добавим картофель
     * @see ru.job4j.solid.experimental.Potato
     * и морковку
     * @see ru.job4j.solid.experimental.Carrot
     * в наш многострадальный борщ
     * @see ru.job4j.solid.experimental.Borsch
     */
    IIngridients addIngridient();
}

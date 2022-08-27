package ru.job4j.solid.experimental;

public interface IVegetables extends IIngridients {

    /**
     * Теперь, в соответствии с принципом OCP мы получили возможность добавлять любые овощи в любое блюдо,
     * не только в суп и все благодаря абстрактному составному классу Ингридиентов.
     * @see IIngridients
     * Например, добавим картофель
     * @see Potato
     * и морковку
     * @see Carrot
     * в наш многострадальный борщ
     * @see Borsch
     */
    IIngridients addIngridient();
}

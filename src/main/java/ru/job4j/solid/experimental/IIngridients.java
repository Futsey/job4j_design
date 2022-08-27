package ru.job4j.solid.experimental;

public interface IIngridients {

    /** Насколько хватает моего понимания в данный момент, теперь мы качественно реализовали принцип OCP
     * У нас появился интерфейс - сервис, который отвечает за формирование ингридиентов
     * Посмотрим как это реализуется
     * @see IVegetables тут мы можем добавить овощи
     * @see IFlesh тут мы можем добавить мясо (да, название интерфейса получилось зловещим)
     */
    IIngridients addIngridient();

    IIngridients getIngridient();
}

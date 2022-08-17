package ru.job4j.solid.experimental;

public interface Cook {

    /** Нарушение принципов SRP и OCP (особенно затесавшийся амлет). Ограничение расширения. На самом высоком абстрактоном уровне
     * пытаемся сузить применимость методов, а это в корне неверно
     * void cookFish();
     * void fryPotato();
     * void omelette();
    */

    /** В этом интерфейсе нарушим принцип OCP, так как одним из обязательных ингридиентов борща сделаем сало,
     * а это не есть верный подход, так как мы на самом высоком уровне абстрации ограничиваем
     * вариации ингридиентов борща.
     * Правильней было бы создать отдельный класс борща со своей кастрюлей, временем приготовления,
     * уровнем огня и ингридиентами, который реализовывал бы интерфейс Борщ() и интерфейс Cook().
     * Исходя из вышеизложенного заключаем что:
     * 1. Абстрация, жестко связывающая нас отдельными аргументами == нарушение принципа OCP;
     * 2. Абстрация, содержащая в аргументах лишь часть необходимых для реализации сущностей
     * (где у нас кастрюли, костер или газовая плита и поварешка?), ставит крест на каком-либо расширении.
     * void cookBorsch(Potato potato, PorkFat porkfat);
     */

    /** По итогу, дабы не нарушать принцип OCP в интерфейсе оставляем один расширяемый, но неизменяемый метод:
     * А вот чтобы приготовить суп, создадим предметный интерфейс
     * @see ru.job4j.solid.experimental.CookSoup
     */
    void cook();
}

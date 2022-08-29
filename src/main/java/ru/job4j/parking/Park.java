package ru.job4j.parking;


/** Головной интерфейс, декларирующий главный процесс проекта - парковку
 */
public interface Park {

    <E> void park(E e);
}

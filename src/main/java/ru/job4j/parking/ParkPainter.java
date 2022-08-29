package ru.job4j.parking;

/** Интерфейс для отражения текущего состояния свободных\занятых мест на парковке
 */
public interface ParkPainter extends Park {

    @Override
    public <E> void park(E e);

    void drawParkScheme();
}

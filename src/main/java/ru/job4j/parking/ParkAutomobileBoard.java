package ru.job4j.parking;

/** Класс является моделью данных парковки для автомобилей
 */
public class ParkAutomobileBoard implements ParkGenerator {

    /** Поля класса призваны:
     * cellEfficiency - оценка наличия свободных мест
     * smallCarCounter - счетчик всех операций по вьъезду\выезду легковых транспортных средств
     * bigCarCounter - счетчик всех операций по вьъезду\выезду грузовых транспортных средств
     * board - хранилище данных
     */
    int cellEfficiency;
    int smallCarCounter;
    int bigCarCounter;
    char[][] board;

    public ParkAutomobileBoard() {
        this.cellEfficiency = cellEfficiency;
        this.smallCarCounter = smallCarCounter;
        this.bigCarCounter = bigCarCounter;
        this.board = board;
    }

    /** Метод реализует логику размещения автомобилей на парковке
     */
    @Override
    public <E> void park(E e) {

    }

    /** Метод создает новую парковку
     */
    @Override
    public boolean createPark() {
        return false;
    }
}

package ru.job4j.parking;

/** Класс является моделью данных для автомобилей
 */
public abstract class Car implements Park {

    int carLength;

    public Car(int carLength) {
        this.carLength = carLength;
    }

    public int getCarLength() {
        return carLength;
    }

    public void setCarLength(int carLength) {
        this.carLength = carLength;
    }
}

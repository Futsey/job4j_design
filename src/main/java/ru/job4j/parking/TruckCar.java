package ru.job4j.parking;

public class TruckCar implements Car {

    int carLength;
    String name;

    public TruckCar(int carLength, String name) {
        this.carLength = carLength;
        this.name = name;
    }

    @Override
    public int getNeededSpot() {
        return carLength;
    }

    @Override
    public String toString() {
        return "TruckCar{"
                + "carlength=" + carLength + " "
                + "name " + name
                + '}';
    }
}

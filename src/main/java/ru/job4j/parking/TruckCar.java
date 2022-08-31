package ru.job4j.parking;

public class TruckCar implements Car {

    private int carLength;
    private String name;

    public TruckCar(int carLength, String name) {
        if (carLength <= PassengerCar.PASSENGERCARLENGTH) {
            try {
                throw new IllegalArgumentException("Car length doesn`t match up truck length");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.carLength = carLength;
        this.name = name;
    }

    @Override
    public int getNeededSpot() {
        return carLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[TruckCar{"
                + "carlength=" + carLength + " "
                + "name " + name
                + "}]";
    }
}

package ru.job4j.solid.lsp.parking;

import java.util.Objects;

public class TruckCar implements Car {

    private int carLength;
    private String name;

    public TruckCar(int carLength, String name) {
        if (carLength <= PassengerCar.PASSENGERCARLENGTH) {
            throw new IllegalArgumentException("Car length doesn`t match up truck length");
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TruckCar truckCar = (TruckCar) o;
        return carLength == truckCar.carLength && Objects.equals(name, truckCar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carLength, name);
    }

    @Override
    public String toString() {
        return "[TruckCar{"
                + "carlength=" + carLength + " "
                + "name " + name
                + "}]";
    }
}

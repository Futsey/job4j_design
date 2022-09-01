package ru.job4j.parking;

import java.util.Objects;

public class PassengerCar implements Car {

    public static final int PASSENGERCARLENGTH = 1;
    private String name;

    public PassengerCar(String name) {
        this.name = name;
    }

    @Override
    public int getNeededSpot() {
        return PASSENGERCARLENGTH;
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
        PassengerCar that = (PassengerCar) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PassengerCar{"
                + "name='" + name + '\''
                + '}';
    }
}

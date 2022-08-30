package ru.job4j.parking;

public class PassengerCar implements Car {

    public static final int PASSENGERCARLENGTH = 1;
    String name;

    public PassengerCar(String name) {
        this.name = name;
    }

    @Override
    public int getNeededSpot() {
        return PASSENGERCARLENGTH;
    }

    @Override
    public String toString() {
        return "PassengerCar{"
                + "carlength=" + PASSENGERCARLENGTH + " "
                + "name " + name
                + '}';
    }
}

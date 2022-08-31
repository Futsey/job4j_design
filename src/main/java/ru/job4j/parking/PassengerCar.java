package ru.job4j.parking;

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
    public String toString() {
        return "PassengerCar{"
                + "name='" + name + '\''
                + '}';
    }
}

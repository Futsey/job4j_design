package ru.job4j.parking;

import java.util.List;

public interface Park {

    boolean addCarInPark(Car auto);
    List<Car> getAllPassengerCar();
    List<Car> getAllTruckCar();
}

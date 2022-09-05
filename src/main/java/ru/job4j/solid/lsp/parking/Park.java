package ru.job4j.solid.lsp.parking;

import java.util.Set;

public interface Park {

    boolean addCarInPark(Car auto);
    Set<Car> getAllPassengerCar();
    Set<Car> getAllTruckCar();
}

package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoard implements Park {

    private final List<Car> passengerCarStore;
    private final List<Car> truckCarStore;
    private int passengerCarSpot;
    private int truckCarSpot;

    public ParkingBoard(int passengerCarSpot, int truckCarSpot) {
        this.passengerCarSpot = passengerCarSpot;
        this.truckCarSpot = truckCarSpot;
        passengerCarStore = new ArrayList<>(passengerCarSpot);
        truckCarStore = new ArrayList<>(truckCarSpot);
    }

    @Override
    public boolean addCarInPark(Car car) {
        boolean rsl = false;
        int carLength = car.getNeededSpot();
        System.out.println("=============");
        if (carLength > PassengerCar.PASSENGERCARLENGTH) {
            if (truckCarSpot < PassengerCar.PASSENGERCARLENGTH && passengerCarSpot >= carLength) {
                System.out.println("Spots at passengerCarSpot before adding: " + passengerCarSpot);
                passengerCarStore.add(car);
                passengerCarSpot -= carLength;
                System.out.println("Truck added to passengerCarSpot. There was no place at truckCarSpot");
                System.out.println("Spots at passengerCarSpot: " + passengerCarSpot + " left");
                rsl = true;
            } else if (truckCarSpot >= PassengerCar.PASSENGERCARLENGTH) {
                System.out.println("Spots at passengerCarSpot before adding: " + passengerCarSpot);
                truckCarStore.add(car);
                truckCarSpot--;
                System.out.println("Truck added to truckCarSpot");
                System.out.println("Spots at truckCarSpot: " + truckCarSpot + " left");
                rsl = true;
            }
        } else if (passengerCarSpot >= PassengerCar.PASSENGERCARLENGTH) {
            System.out.println("Spots at passengerCarSpot before adding: " + passengerCarSpot);
            passengerCarStore.add(car);
            passengerCarSpot--;
            System.out.println("PassengerCar added to passengerCarSpot");
            System.out.println("Spots at passengerCarSpot: " + passengerCarSpot + " left");
            rsl = true;
        } else {
            System.out.println("There is no spot to park your car " + car);
        }
        return rsl;
    }

    @Override
    public List<Car> getAllPassengerCar() {
        return new ArrayList<>(passengerCarStore);
    }

    @Override
    public List<Car> getAllTruckCar() {
        return new ArrayList<>(truckCarStore);
    }
}

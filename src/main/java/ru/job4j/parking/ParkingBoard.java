package ru.job4j.parking;

import java.util.HashSet;
import java.util.Set;

public class ParkingBoard implements Park {

    private final Set<Car> passengerCarStore;
    private final Set<Car> truckCarStore;
    private int passengerCarSpot;
    private int truckCarSpot;

    public ParkingBoard(int passengerCarSpot, int truckCarSpot) {
        this.passengerCarSpot = passengerCarSpot;
        this.truckCarSpot = truckCarSpot;
        passengerCarStore = new HashSet<>(passengerCarSpot);
        truckCarStore = new HashSet<>(truckCarSpot);
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
    public Set<Car> getAllPassengerCar() {
        return new HashSet<>(passengerCarStore);
    }

    @Override
    public Set<Car> getAllTruckCar() {
        return new HashSet<>(truckCarStore);
    }
}

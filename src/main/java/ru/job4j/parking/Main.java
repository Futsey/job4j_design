package ru.job4j.parking;

public class Main {

    public static void main(String[] args) {
        ParkingBoard board = new ParkingBoard(3, 2);
        Car pCar = new PassengerCar("pCar");
        board.addCarInPark(pCar);
        System.out.println(board.getAllPassengerCar());
        Car tCar = new TruckCar(2, "tCar");
        Car tCar1 = new TruckCar(2, "tCar1");
        Car tCar2 = new TruckCar(2, "tCar2");
        board.addCarInPark(tCar);
        System.out.println(board.getAllTruckCar());
        board.addCarInPark(tCar1);
        System.out.println(board.getAllTruckCar());
        board.addCarInPark(tCar2);
        System.out.println(board.getAllTruckCar());
        System.out.println(board.getAllPassengerCar());
        Car pCar1 = new PassengerCar("pCar1");
        board.addCarInPark(pCar1);
        System.out.println(board.getAllTruckCar());
        System.out.println(board.getAllPassengerCar());
    }
}

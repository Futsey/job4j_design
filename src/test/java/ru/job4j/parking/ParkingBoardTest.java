package ru.job4j.parking;

import org.junit.jupiter.api.Test;
import ru.job4j.solid.lsp.parking.*;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoardTest {

    @Test
    public void whenParkPassengerCar() {
        Park board = new ParkingBoard(1, 0);
        Car pCar = new PassengerCar("pCar");
        assertTrue(board.addCarInPark(pCar));
    }

    @Test
    public void whenParkTruckCar() {
        Park board = new ParkingBoard(0, 1);
        Car tCar = new TruckCar(2, "tCar");
        assertTrue(board.addCarInPark(tCar));
    }

    @Test
    public void whenParkTruckCarOnPassengerCarSpot() {
        Park board = new ParkingBoard(2, 0);
        Car tCar = new TruckCar(2, "tCar");
        assertTrue(board.addCarInPark(tCar));
    }

    @Test
    public void whenHaveNoSpotsForTruckCarAndNoneSpaceAtPassengerCarSpot() {
        Park board = new ParkingBoard(1, 0);
        Car tCar = new TruckCar(2, "tCar");
        assertFalse(board.addCarInPark(tCar));
    }

    @Test
    public void whenHaveNoSpotsForPassengerCar() {
        Park board = new ParkingBoard(1, 0);
        Car pCar = new PassengerCar("pCar");
        board.addCarInPark(pCar);
        Car pCar1 = new PassengerCar("pCar1");
        assertFalse(board.addCarInPark(pCar1));
    }

    @Test
    public void whenHaveNoSpotsForTruckCar() {
        Park board = new ParkingBoard(1, 0);
        Car tCar = new TruckCar(2, "tCar");
        board.addCarInPark(tCar);
        Car tCar1 = new TruckCar(2, "tCar");
        assertFalse(board.addCarInPark(tCar1));
    }

    @Test
    void whenLengthIsNotEnough() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
             Car tCar = new TruckCar(1, "tCar");
        });
        assertEquals("Car length doesn`t match up truck length",
                exception.getMessage());
    }
}
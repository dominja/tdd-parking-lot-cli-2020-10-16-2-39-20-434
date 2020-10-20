package com.oocl.cultivation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private int slot = 10;

    private final Map<ParkingTicket, Car> parkingLotSpace = new ConcurrentHashMap<>();

    public ParkingLot(int slot) {
        this.slot = slot;
    }

    public ParkingLot() {
    }

    ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotSpace.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        Car parkedCar = parkingLotSpace.get(parkingTicket);
        parkingLotSpace.remove(parkingTicket);
        return parkedCar;
    }

    boolean isFull() {
        return parkingLotSpace.size() >= slot;
    }

    int getAvailableSlot() {
        return slot - parkingLotSpace.size();
    }


    Map<ParkingTicket, Car> getParkedCars() {
        return parkingLotSpace;
    }

    double getAverageAvailableSlot() {
        return (double) getAvailableSlot() / slot * 100;
    }
}

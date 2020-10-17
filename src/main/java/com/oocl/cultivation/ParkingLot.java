package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int slot = 10;
    private Map<ParkingTicket, Car> parkingLotSpace = new HashMap<>();

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

    int getAvailableSlot() {
        return slot - parkingLotSpace.size();
    }

    boolean isFull() {
        return parkingLotSpace.size() >= slot;
    }

    Map<ParkingTicket, Car> getParkedCars() {
        return parkingLotSpace;
    }
}

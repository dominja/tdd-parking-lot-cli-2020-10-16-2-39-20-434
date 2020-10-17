package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int slot = 10;
    private int carsParked;
    private Map<ParkingTicket, Car> parkingLotSpace = new HashMap<>();

    public ParkingLot(int slot, int carsParked) {
        this.slot = slot;
        this.carsParked = carsParked;
    }

    public ParkingLot() {
    }

    ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotSpace.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        return parkingLotSpace.get(parkingTicket);
    }

    public int getAvailableSlot() {
        return slot - carsParked;
    }

    boolean isFull() {
        return parkingLotSpace.size() >= slot;
    }
    Map<ParkingTicket, Car> getParkedCars() {
        return parkingLotSpace;
    }
}

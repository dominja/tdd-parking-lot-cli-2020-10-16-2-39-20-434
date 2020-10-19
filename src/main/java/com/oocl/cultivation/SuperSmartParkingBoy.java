package com.oocl.cultivation;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }
    @Override
    public ParkingLot pickParkingLot() {
        return getParkingLots().stream()
                .max(Comparator.comparing(ParkingLot::getAverageAvailableSlot))
                .filter(lot ->!lot.isFull())
                .orElseThrow(() -> new RuntimeException(NOT_ENOUGH_POSITION));
    }
}

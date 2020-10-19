package com.oocl.cultivation;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    private static final String NOT_ENOUGH_POSITION = "Not Enough Position";

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    //comparator max
    @Override
    public ParkingLot pickParkingLot() {
        return getParkingLots().stream().max(Comparator.comparing(ParkingLot::getAverageAvailableSlot))
                .filter(lot ->!lot.isFull())
                .orElseThrow(() -> new RuntimeException(NOT_ENOUGH_POSITION));
    }
}

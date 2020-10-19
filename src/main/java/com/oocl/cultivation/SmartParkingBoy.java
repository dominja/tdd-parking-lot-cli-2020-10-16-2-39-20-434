package com.oocl.cultivation;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingLot pickParkingLot() {
        return getParkingLots().stream()
                .max(Comparator.comparing(ParkingLot::getAvailableSlot))
                .orElseThrow(() -> new NoAvailableSpacesException(NOT_ENOUGH_POSITION));
    }
}

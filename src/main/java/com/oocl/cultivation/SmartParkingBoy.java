package com.oocl.cultivation;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot...parkingLot) {
        super(parkingLot);
    }

    public ParkingLot pickParkingLot() {
        return getParkingLotList().stream()
                .max(Comparator.comparing(ParkingLot::getAvailableSlot))
                .filter(ParkingLot::isNotFull)
                .orElseThrow(() -> new NoAvailableSpacesException(NOT_ENOUGH_POSITION));
    }
}

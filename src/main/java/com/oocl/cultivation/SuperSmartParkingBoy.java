package com.oocl.cultivation;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }
    public ParkingLot pickParkingLot() {
        return getParkingLotList().stream()
                .max(Comparator.comparing(ParkingLot::getAverageAvailableSlot))
                .filter(ParkingLot::isNotFull)
                .orElseThrow(() -> new RuntimeException(NOT_ENOUGH_POSITION));
    }
}

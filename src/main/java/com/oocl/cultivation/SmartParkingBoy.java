package com.oocl.cultivation;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> listParkingLots;

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingLot pickParkingLot() {
        return listParkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailableSlot))
                .orElseThrow(() -> new NoAvailableSpacesException("Not Enough Position"));
    }

    @Override
    public void setListParkingLots(List<ParkingLot> parkingLots) {
        this.listParkingLots = parkingLots;
    }

}

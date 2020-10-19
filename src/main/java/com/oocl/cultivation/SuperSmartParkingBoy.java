package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> listParkingLots;

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    //comparator max
    @Override
    public ParkingLot pickParkingLot() {
        return listParkingLots.stream().reduce((parkingLot, parkingLot2) -> parkingLot.getAverageAvailableSlot() >
                parkingLot2.getAverageAvailableSlot() ? parkingLot : parkingLot2)
                .orElseThrow(() -> new NoAvailableSpacesException("Not Enough Position"));
    }
//remove this method
    @Override
    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.listParkingLots = parkingLots;
    }

}

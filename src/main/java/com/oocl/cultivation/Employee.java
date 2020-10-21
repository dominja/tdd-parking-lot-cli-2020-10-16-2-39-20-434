package com.oocl.cultivation;

import java.util.List;

abstract class Employee {
    List<ParkingLot> parkingLots;

    Employee(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    List<ParkingLot> getParkingLotList() {
        return parkingLots;
    }

    abstract ParkingTicket park(Car car);

    abstract Car fetch(ParkingTicket parkingTicket);

    public abstract ParkingLot pickParkingLot();

}

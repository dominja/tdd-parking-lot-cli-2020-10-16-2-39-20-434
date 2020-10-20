package com.oocl.cultivation;

import java.util.List;

import static java.util.Collections.singletonList;

abstract class Employee {
    List<ParkingLot> parkingLots;

    Employee(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    Employee(ParkingLot parkingLot) {
        this.parkingLots = singletonList(parkingLot);
    }

    List<ParkingLot> getParkingLotList() {
        return parkingLots;
    }

    abstract ParkingTicket park(Car car);

    abstract Car fetch(ParkingTicket parkingTicket);


}

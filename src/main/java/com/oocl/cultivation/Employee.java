package com.oocl.cultivation;

import java.util.List;

import static java.util.Collections.singletonList;

abstract class Employee {
    private List<ParkingLot> parkingLots;

    Employee(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    Employee(ParkingLot parkingLot) {
        this.parkingLots = singletonList(parkingLot);
    }

    List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    abstract ParkingTicket park(Car car);

    abstract Car fetch(ParkingTicket parkingTicket);


}

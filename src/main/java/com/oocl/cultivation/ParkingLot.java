package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<ParkingTicket, Car> parkingLotSpace = new HashMap<>(10);

    ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotSpace.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket) {
        return parkingLotSpace.get(parkingTicket);
    }
}

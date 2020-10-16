package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<ParkingTicket, Car> parkingLotSpace = new HashMap<>(10);

    ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotSpace.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicket {
        Car ticket = parkingLotSpace.get(parkingTicket);
        if(ticket!=null) {
            return ticket;
        }else{
            throw new UnrecognizedParkingTicket("Unrecognized Parking Ticket");
        }
    }
}

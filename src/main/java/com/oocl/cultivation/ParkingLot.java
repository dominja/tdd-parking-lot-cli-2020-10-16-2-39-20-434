package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<ParkingTicket, Car> parkingLotSpace = new HashMap<>(10);

    ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingLotSpace.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetch(ParkingTicket parkingTicket){
        Car ticket = parkingLotSpace.get(parkingTicket);
        if(ticket!=null) {
            return ticket;
        }else if(parkingTicket==null){
            throw new NoTicketException("Please Provide Your Parking Ticket");
        }else{
            throw new UnrecognizedParkingTicket("Unrecognized Parking Ticket");
        }
    }
}

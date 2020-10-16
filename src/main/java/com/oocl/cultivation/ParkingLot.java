package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<ParkingTicket, Car> parkingLotSpace = new HashMap<>(10);

    ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        if(parkingLotSpace.size()!=10) {
            parkingLotSpace.put(parkingTicket, car);
        }else {
            throw new NoAvailableSpacesException("Not Enough Position");
        }
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

package com.oocl.cultivation;

public interface ITasks {
    Car fetch(ParkingTicket parkingTicket);

    ParkingTicket park(Car car);

     ParkingLot pickParkingLot();

}

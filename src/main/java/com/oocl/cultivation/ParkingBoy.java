package com.oocl.cultivation;

public class ParkingBoy extends ParkingTicket {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        super();
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return new ParkingTicket(car);
    }
}

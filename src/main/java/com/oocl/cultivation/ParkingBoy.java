package com.oocl.cultivation;

import java.util.Arrays;

public class ParkingBoy extends Employee {
    static final String NOT_ENOUGH_POSITION = "Not Enough Position";
    static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please Provide Your Parking Ticket";
    static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized Parking Ticket";
    private NormalParkingBoySkill normalParkingBoySkill;

    public ParkingBoy(ParkingLot... parkingLot) {
        super(Arrays.asList(parkingLot));
        this.normalParkingBoySkill = new NormalParkingBoySkill(Arrays.asList(parkingLot));
    }

    public ParkingTicket park(Car car) {
        return normalParkingBoySkill.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return normalParkingBoySkill.fetch(parkingTicket);
    }

}

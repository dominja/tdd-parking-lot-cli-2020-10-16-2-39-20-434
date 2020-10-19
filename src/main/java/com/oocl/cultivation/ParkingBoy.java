package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingBoy {
    static final String NOT_ENOUGH_POSITION = "Not Enough Position";
    private static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please Provide Your Parking Ticket";
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized Parking Ticket";
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<>();
        this.parkingLots.add(parkingLot);
    }

    List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot chosenParkingLot = pickParkingLot();
        return chosenParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream().filter(lot -> lot.getParkedCars().containsKey(validateTicket(parkingTicket)))
                .map(lot -> lot.fetch(parkingTicket)).findFirst()
                .orElseThrow(() -> new UnrecognizedParkingTicket(UNRECOGNIZED_PARKING_TICKET));
    }

    private ParkingTicket validateTicket(ParkingTicket parkingTicket) {
        return Optional.ofNullable(parkingTicket).orElseThrow(() ->
                new NoTicketException(PLEASE_PROVIDE_YOUR_PARKING_TICKET));
    }

    public ParkingLot pickParkingLot() {
        return parkingLots.stream().filter(lot -> !lot.isFull()).findFirst()
                .orElseThrow(() -> new NoAvailableSpacesException(NOT_ENOUGH_POSITION));
    }
}

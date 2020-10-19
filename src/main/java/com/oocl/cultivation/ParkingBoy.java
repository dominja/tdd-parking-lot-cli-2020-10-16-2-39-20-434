package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private static final String NOT_ENOUGH_POSITION = "Not Enough Position";
    private static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please Provide Your Parking Ticket";
    private List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<>();
        this.parkingLots.add(parkingLot);
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot chosenParkingLot = pickParkingLot();
        return chosenParkingLot.park(car);
    }

    //todo: convert to optional
    public Car fetch(ParkingTicket parkingTicket) {
        Car carFetched = new Car();
        if (checkTicket(parkingTicket)) {
            for (ParkingLot parkingLot : parkingLots) {
                carFetched = parkingLot.fetch(parkingTicket);
            }
            return carFetched;
        } else {
            throw new UnrecognizedParkingTicket("Unrecognized Parking Ticket");
        }
    }

    private boolean checkTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NoTicketException(PLEASE_PROVIDE_YOUR_PARKING_TICKET);
        }
        return parkingLots.stream().anyMatch(lot ->
                lot.getParkedCars().containsKey(parkingTicket));
    }

    public ParkingLot pickParkingLot() {
        return parkingLots.stream().filter(lot -> !lot.isFull()).findFirst()
                .orElseThrow(() -> new NoAvailableSpacesException(NOT_ENOUGH_POSITION));
    }
}

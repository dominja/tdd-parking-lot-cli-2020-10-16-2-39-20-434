package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> listParkingLots;


    public ParkingBoy(ParkingLot parkingLot) {
        this.listParkingLots = new ArrayList<>();
        this.listParkingLots.add(parkingLot);
    }

    public void setListParkingLots(List<ParkingLot> listParkingLots) {
        this.listParkingLots = listParkingLots;
    }

    public ParkingTicket park(Car car) {
        ParkingLot chosenParkingLot = pickParkingLot();
        return chosenParkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car carFetched = new Car();
        if (checkTicket(parkingTicket)) {
            for (ParkingLot parkingLot : listParkingLots) {
                carFetched = parkingLot.fetch(parkingTicket);
            }
            return carFetched;
        } else {
            throw new UnrecognizedParkingTicket("Unrecognized Parking Ticket");
        }
    }

    private boolean checkTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NoTicketException("Please Provide Your Parking Ticket");
        }
        return listParkingLots.stream().anyMatch(parkingLots ->
                parkingLots.getParkedCars().containsKey(parkingTicket));
    }

    private ParkingLot pickParkingLot() {
        for (ParkingLot parkingLotSlot : listParkingLots) {
            if (!parkingLotSlot.isFull()) {
                return parkingLotSlot;
            }
        }
        throw new NoAvailableSpacesException("Not Enough Position");
    }
}

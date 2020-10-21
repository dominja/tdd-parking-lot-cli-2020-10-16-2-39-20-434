package com.oocl.cultivation;

import java.util.List;
import java.util.Optional;

import static com.oocl.cultivation.ParkingBoy.*;

public class BasicParkingSkill implements ITasks {
    private List<ParkingLot> parkingLots;

    BasicParkingSkill(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    public ParkingTicket park(Car car) {
        ParkingLot pickParkingLot = pickParkingLot();
        return pickParkingLot.park(car);
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
    @Override
    public ParkingLot pickParkingLot() {
        return parkingLots.stream().filter(ParkingLot::isNotFull).findFirst()
                .orElseThrow(() -> new NoAvailableSpacesException(NOT_ENOUGH_POSITION));
    }
}

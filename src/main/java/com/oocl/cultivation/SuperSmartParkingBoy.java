package com.oocl.cultivation;

import java.util.Arrays;
import java.util.Comparator;

import static com.oocl.cultivation.ParkingBoy.NOT_ENOUGH_POSITION;

public class SuperSmartParkingBoy extends Employee {

    private BasicParkingSkill basicParkingSkill;

    SuperSmartParkingBoy(ParkingLot... parkingLot) {
        super(Arrays.asList(parkingLot));
        this.basicParkingSkill = new BasicParkingSkill(Arrays.asList(parkingLot));
    }

    public ParkingTicket park(Car car) {
        return basicParkingSkill.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return basicParkingSkill.fetch(parkingTicket);
    }

    @Override
    public ParkingLot pickParkingLot() {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAverageAvailableSlot))
                .filter(ParkingLot::isNotFull)
                .orElseThrow(() -> new RuntimeException(NOT_ENOUGH_POSITION));

    }
}

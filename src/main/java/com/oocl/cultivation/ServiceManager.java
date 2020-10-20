package com.oocl.cultivation;

import java.util.Arrays;
import java.util.List;

public class ServiceManager extends Employee {

    private List<ParkingBoy> parkingBoys;
    private NormalParkingBoySkill normalParkingBoySkill;

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ServiceManager(ParkingLot... parkingLot) {
        super(Arrays.asList(parkingLot));
        normalParkingBoySkill = new NormalParkingBoySkill(Arrays.asList(parkingLot));
    }

    @Override
    ParkingTicket park(Car car) {
        return normalParkingBoySkill.park(car);
    }

    @Override
    Car fetch(ParkingTicket parkingTicket) {
        return normalParkingBoySkill.fetch(parkingTicket);
    }

    public void setParkingBoyList(List<ParkingBoy> parkingBoyList) {
        this.parkingBoys = parkingBoyList;
    }

    public ParkingTicket orderParkingBoyToPark(Car car, ParkingBoy parkingBoy) {
        if (parkingBoys.contains(parkingBoy)) {
            return parkingBoy.park(car);
        }
        return null;
    }

    public Car orderParkingBoyToFetch(ParkingTicket parkingTicket, ParkingBoy parkingBoy) {
        if (parkingBoys.contains(parkingBoy)) {
            return parkingBoy.fetch(parkingTicket);
        }
        return null;
    }
}

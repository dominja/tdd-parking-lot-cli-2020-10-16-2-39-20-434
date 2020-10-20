package com.oocl.cultivation;

import java.util.List;

public class ServiceManager extends ParkingBoy {

    private List<ParkingBoy> parkingBoys;

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ServiceManager(ParkingLot parkingLot) {
        super(parkingLot);

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

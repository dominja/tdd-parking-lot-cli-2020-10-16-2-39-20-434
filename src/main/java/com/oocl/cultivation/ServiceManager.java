package com.oocl.cultivation;

import java.util.List;

public class ServiceManager extends ParkingBoy {

    private List<ParkingBoy> parkingBoyList;
    public ServiceManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public List<ParkingBoy> getParkingBoyList(List<ParkingBoy> parkingBoyList) {
         this.parkingBoyList=parkingBoyList;
         return this.parkingBoyList;
    }

    public ParkingTicket orderParkingBoyToPark(Car car, ParkingBoy parkingBoy) {
         if(parkingBoyList.contains(parkingBoy)) {
            return parkingBoy.park(car);
         }
        return null;
    }
}

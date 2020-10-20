package com.oocl.cultivation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.oocl.cultivation.ParkingBoy.UNRECOGNIZED_PARKING_TICKET;

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

    public ParkingTicket orderParkingBoyToPark(Car car) {
        return parkingBoys.get(0).park(car);
    }

    public Car orderParkingBoyToFetch(ParkingTicket parkingTicket) {
        Optional<ParkingBoy> managedParkingBoy = getParkingBoyToCommand(parkingTicket);

        return managedParkingBoy.map(parkingBoy -> parkingBoy.fetch(parkingTicket))
                .orElseThrow(() -> new UnrecognizedParkingTicket(UNRECOGNIZED_PARKING_TICKET));
    }

    private Optional<ParkingBoy> getParkingBoyToCommand(ParkingTicket parkingTicket) {
        return parkingBoys.stream().filter(parkingBoy ->
                parkingBoy.getParkingLotList().stream()
                        .anyMatch(parkingLot -> parkingLot.getParkedCars().containsKey(parkingTicket)))
                .findFirst();
    }
}

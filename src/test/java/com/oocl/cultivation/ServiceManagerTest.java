package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ServiceManagerTest {
    @Test
    void should_return_list_of_parking_boys_to_management_list_manage_parking_boys() {
        //Given
        List<ParkingBoy> parkingBoys;
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        List<ParkingBoy> listParkingBoy = Arrays.asList(parkingBoy, smartParkingBoy);
        //When
        parkingBoys = serviceManager.getParkingBoyList(listParkingBoy);
        // Then
        Assertions.assertNotNull(parkingBoys);
    }

    @Test
    void should_return_parking_ticket_when_park_given_car_to_boy_which_is_on_list_and_parking_lot_is_managed() {
        //Given
        Car car = new Car();
        ParkingTicket ticket;
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy normalParkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        normalParkingBoy.setListParkingLots(parkingLots);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy, smartParkingBoy);
        serviceManager.getParkingBoyList(listParkingBoy);
        //When
        ticket = serviceManager.orderParkingBoyToPark(car, normalParkingBoy);
        // Then
        Assertions.assertNotNull(ticket);
    }
}

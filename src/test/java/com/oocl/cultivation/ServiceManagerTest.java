package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNotNull(parkingBoys);
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
        assertNotNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_car_to_boy_which_is_not_on_list_and_parking_lot_is_managed() {
        //Given
        Car car = new Car();
        ParkingTicket ticket;
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy normalParkingBoy1 = new ParkingBoy(new ParkingLot());
        ParkingBoy normalParkingBoy2 = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        normalParkingBoy1.setListParkingLots(parkingLots);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy1, normalParkingBoy2);
        serviceManager.getParkingBoyList(listParkingBoy);
        //When
        ticket = serviceManager.orderParkingBoyToPark(car, smartParkingBoy);
        // Then
        assertNull(ticket);
    }

    @Test
    void should_return_right_count_of_parking_lot_slot_when_park_given_to_boy_and_can_only_access_his_own_parkinglot() {
        //Given
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());

        ParkingBoy normalParkingBoy1 = new ParkingBoy(new ParkingLot());
        ParkingBoy normalParkingBoy2 = new ParkingBoy(new ParkingLot());

        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        ParkingLot parkingLot3 = new ParkingLot(3);
        ParkingLot parkingLot4 = new ParkingLot(4);

        List<ParkingLot> parkingLotsForNormalBoy1 = Arrays.asList(parkingLot1, parkingLot2);
        List<ParkingLot> parkingLotsForNormalBoy2 = Arrays.asList(parkingLot3, parkingLot4);

        normalParkingBoy1.setListParkingLots(parkingLotsForNormalBoy1);
        normalParkingBoy2.setListParkingLots(parkingLotsForNormalBoy2);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy1, normalParkingBoy2);
        serviceManager.getParkingBoyList(listParkingBoy);

        //When
        serviceManager.orderParkingBoyToPark(car, normalParkingBoy1);
        int countParkedCarsNormalBoy1 = parkingLot1.getParkedCars().size();
        int countParkedCarsNormalBoy2 = parkingLot2.getParkedCars().size();
        // Then
        assertEquals(1, countParkedCarsNormalBoy1);
        assertEquals(0, countParkedCarsNormalBoy2);
    }

    @Test
    void should_return_parking_ticket_when_park_given_car_to_service_manager() {
        //given
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        // when
        ParkingTicket ticket = serviceManager.park(car);
        //then
        assertNotNull(ticket);
    }
}

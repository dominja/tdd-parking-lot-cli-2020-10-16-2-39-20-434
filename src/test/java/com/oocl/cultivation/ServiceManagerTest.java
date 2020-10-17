package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        serviceManager.setParkingBoyList(listParkingBoy);
        parkingBoys = serviceManager.getParkingBoys();
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
        serviceManager.setParkingBoyList(listParkingBoy);
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
        serviceManager.setParkingBoyList(listParkingBoy);
        //When
        ticket = serviceManager.orderParkingBoyToPark(car, smartParkingBoy);
        // Then
        assertNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_to_boy_and_can_only_access_his_own_parkinglot() {
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
        serviceManager.setParkingBoyList(listParkingBoy);

        //When
        ParkingTicket ticket = serviceManager.orderParkingBoyToPark(car, normalParkingBoy1);
        // Then
        assertNull(parkingLot3.fetch(ticket));
        assertNull(parkingLot4.fetch(ticket));
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

    @Test
    void should_return_null_when_parked_to_wrong_parking_lot_given_car_to_service_manager_() {
        //given
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingLot parkingLot3 = new ParkingLot(3);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        serviceManager.setListParkingLots(parkingLots);
        // when
        ParkingTicket ticket = serviceManager.park(car);
        //then
        assertNull(parkingLot3.fetch(ticket));
    }

    @Test
    void should_return_car_when_fetch_given_ticket_to_service_manager() {
        //given
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingTicket ticket = serviceManager.park(car);
        // when
        Car returnedCar = serviceManager.fetch(ticket);
        //then
        assertSame(car, returnedCar);
    }

    @Test
    void should_return_null_when_fetch_given_no_ticket_to_boy_which_is_not_on_list_and_parking_lot_is_managed() {
        //Given
        Car returnCar = new Car();
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy normalParkingBoy1 = new ParkingBoy(new ParkingLot());
        ParkingBoy normalParkingBoy2 = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        normalParkingBoy1.setListParkingLots(parkingLots);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy1, normalParkingBoy2);
        serviceManager.setParkingBoyList(listParkingBoy);
        //When
        returnCar = serviceManager.orderParkingBoyToFetch(null, smartParkingBoy);
        // Then
        assertNull(returnCar);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_message_when_manager_asked_to_fetch_given_invalid_ticket_to_boy() {
        //Given
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy normalParkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        normalParkingBoy.setListParkingLots(parkingLots);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy, smartParkingBoy);
        serviceManager.setParkingBoyList(listParkingBoy);
        ParkingTicket invalidTicket = new ParkingTicket();
        //then
        assertThrows(UnrecognizedParkingTicket.class, () -> {
            //When
            serviceManager.orderParkingBoyToFetch(invalidTicket, normalParkingBoy);
        });
    }
    @Test
    void should_return_NoTicketException_message_when_manager_asked_to_fetch_given_no_ticket_to_boy() {
        //Given
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy normalParkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        normalParkingBoy.setListParkingLots(parkingLots);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy, smartParkingBoy);
        serviceManager.setParkingBoyList(listParkingBoy);
        //then
        assertThrows(NoTicketException.class, () -> {
            //when
            serviceManager.orderParkingBoyToFetch(null, normalParkingBoy);
        });
    }
    @Test
    void should_return_NoAvailableSpacesException_message_when_manager_asked_to_fetch_given_no_ticket_to_boy() {
        //Given
        Car car = new Car();
        ServiceManager serviceManager = new ServiceManager(new ParkingLot());
        ParkingBoy normalParkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);

        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        normalParkingBoy.setListParkingLots(parkingLots);

        List<ParkingBoy> listParkingBoy = Arrays.asList(normalParkingBoy, smartParkingBoy);
        serviceManager.setParkingBoyList(listParkingBoy);
        IntStream.range(0, 4).forEach(cars -> {
            Car carsNew = new Car();
            serviceManager.orderParkingBoyToPark(carsNew, normalParkingBoy);
        });
        //then
        assertThrows(NoAvailableSpacesException.class, () -> {
            // when
            serviceManager.orderParkingBoyToPark(car, normalParkingBoy);
        });
    }
}

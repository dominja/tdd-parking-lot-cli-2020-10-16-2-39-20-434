package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceManagerTest {
    private Car car;
    private ParkingBoy normalParkingBoy;
    private ServiceManager serviceManager;
    private SmartParkingBoy smartParkingBoy;
    private ParkingTicket ticket;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private ParkingLot parkingLot3;


    @BeforeEach
    void setUp() {
        //given
        car = new Car();
        serviceManager = new ServiceManager(new ParkingLot());
        normalParkingBoy = new ParkingBoy(new ParkingLot());
        smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        parkingLot1 = new ParkingLot(1);
        parkingLot2 = new ParkingLot(3);
        parkingLot3 = new ParkingLot(4);
    }

    private void addParkingBoyToList() {
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));
    }

    void setParkingLotToParkingBoy() {
        normalParkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
    }

    @Test
    void should_return_list_of_parking_boys_when_set_parking_boy_list_given_parking_boys() {
        //When
        addParkingBoyToList();
        // Then
        assertNotNull(serviceManager.getParkingBoys());
    }

    @Test
    void should_return_parking_ticket_when_park_given_car_to_boy_which_is_on_list_and_parking_lot_is_managed() {
        //Given
        setParkingLotToParkingBoy();
        addParkingBoyToList();
        //When
        ticket = serviceManager.orderParkingBoyToPark(car);
        // Then
        assertNotNull(ticket);
    }

    //    @Test
    void should_return_null_when_park_given_car_to_boy_which_is_not_on_list_and_parking_lot_is_managed() {
        //Given
        setParkingLotToParkingBoy();
        addParkingBoyToList();
        //When
        ticket = serviceManager.orderParkingBoyToPark(car);
        // Then
        assertNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_to_boy_and_can_only_access_his_own_parkinglot() {
        //Given
        ParkingLot parkingLot4 = new ParkingLot(4);
        setParkingLotToParkingBoy();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot3, parkingLot4);
        addParkingBoyToList();

        //When
        ParkingTicket ticket = serviceManager.orderParkingBoyToPark(car);
        // Then
        assertNull(parkingLot3.fetch(ticket));
        assertNull(parkingLot4.fetch(ticket));
    }

    @Test
    void should_return_parking_ticket_when_park_given_car_to_service_manager() {
        // when & then
        assertNotNull(serviceManager.park(car));
    }

    @Test
    void should_return_null_when_parked_to_wrong_parking_lot_given_car_to_service_manager_() {
        //given
        serviceManager = new ServiceManager(parkingLot1, parkingLot2);
        // when
        ParkingTicket ticket = serviceManager.park(car);
        //then
        assertNull(parkingLot3.fetch(ticket));
    }

    @Test
    void should_return_car_when_fetch_given_ticket_to_service_manager() {
        // when
        Car returnedCar = serviceManager.fetch(serviceManager.park(car));
        //then
        assertSame(car, returnedCar);
    }

    //    @Test
    void should_return_null_when_fetch_given_no_ticket_to_boy_which_is_not_on_list_and_parking_lot_is_managed() {
        //Given
        setParkingLotToParkingBoy();
        addParkingBoyToList();
        //When
        car = serviceManager.orderParkingBoyToFetch(null);
        // Then
        assertNull(car);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_message_when_manager_asked_to_fetch_given_invalid_ticket_to_boy() {
        //Given
        setParkingLotToParkingBoy();
        addParkingBoyToList();
        ticket = new ParkingTicket();
        //then
        UnrecognizedParkingTicket unrecognizedParkingTicket = assertThrows(UnrecognizedParkingTicket.class, () -> {
            //When
            serviceManager.orderParkingBoyToFetch(ticket);
        });
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicket.getMessage());
    }

    //    @Test
    void should_return_NoTicketException_message_when_manager_asked_to_fetch_given_no_ticket_to_boy() {
        //Given
        setParkingLotToParkingBoy();
        addParkingBoyToList();
        //then
        NoTicketException noTicketException = assertThrows(NoTicketException.class, () -> {
            //when
            serviceManager.orderParkingBoyToFetch(null);
        });
        assertEquals("Please Provide Your Parking Ticket", noTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailableSpacesException_message_when_manager_asked_to_fetch_given_no_ticket_to_boy() {
        //Given
        setParkingLotToParkingBoy();
        addParkingBoyToList();
        IntStream.range(0, 4).forEach(cars -> {
            serviceManager.orderParkingBoyToPark(car);
        });
        //then
        assertThrows(NoAvailableSpacesException.class, () -> {
            // when
            serviceManager.orderParkingBoyToPark(car);
        });
    }


}

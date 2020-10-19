package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ServiceManagerTest {
    private Car car;
    private ParkingBoy normalParkingBoy;
    private ServiceManager serviceManager;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;
    private ParkingTicket ticket;
    private ParkingLot parkingLot1 = new ParkingLot(1);
    private ParkingLot parkingLot2 = new ParkingLot(3);
    private ParkingLot parkingLot3 = new ParkingLot(3);


    @BeforeEach
    void setUp() {
        //given
        car = new Car();
        serviceManager = new ServiceManager(new ParkingLot());
        normalParkingBoy = new ParkingBoy(new ParkingLot());
        smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
    }

    @Test
    void should_return_list_of_parking_boys_when_set_parking_boy_list_given_parking_boys() {
        //When
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));
        List<ParkingBoy> parkingBoys = serviceManager.getParkingBoys();
        // Then
        assertNotNull(parkingBoys);
    }

    @Test
    void should_return_parking_ticket_when_park_given_car_to_boy_which_is_on_list_and_parking_lot_is_managed() {
        //Given
        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));
        //When
        ticket = serviceManager.orderParkingBoyToPark(car, normalParkingBoy);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_car_to_boy_which_is_not_on_list_and_parking_lot_is_managed() {
        //Given
        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, superSmartParkingBoy));
        //When
        ticket = serviceManager.orderParkingBoyToPark(car, smartParkingBoy);
        // Then
        assertNull(ticket);
    }

    @Test
    void should_return_null_when_park_given_to_boy_and_can_only_access_his_own_parkinglot() {
        //Given
        ParkingLot parkingLot4 = new ParkingLot(4);

        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        smartParkingBoy.setParkingLots(Arrays.asList(parkingLot3, parkingLot4));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));

        //When
        ParkingTicket ticket = serviceManager.orderParkingBoyToPark(car, normalParkingBoy);
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
        serviceManager.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
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

    @Test
    void should_return_null_when_fetch_given_no_ticket_to_boy_which_is_not_on_list_and_parking_lot_is_managed() {
        //Given
        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, superSmartParkingBoy));
        //When
        car = serviceManager.orderParkingBoyToFetch(null, smartParkingBoy);
        // Then
        assertNull(car);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_message_when_manager_asked_to_fetch_given_invalid_ticket_to_boy() {
        //Given
        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));
        ticket = new ParkingTicket();
        //then
        UnrecognizedParkingTicket unrecognizedParkingTicket = assertThrows(UnrecognizedParkingTicket.class, () -> {
            //When
            serviceManager.orderParkingBoyToFetch(ticket, normalParkingBoy);
        });
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicket.getMessage());
    }

    @Test
    void should_return_NoTicketException_message_when_manager_asked_to_fetch_given_no_ticket_to_boy() {
        //Given
        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));
        //then
        NoTicketException noTicketException=assertThrows(NoTicketException.class, () -> {
            //when
            serviceManager.orderParkingBoyToFetch(null, normalParkingBoy);
        });
        assertEquals("Please Provide Your Parking Ticket", noTicketException.getMessage());
    }

    @Test
    void should_return_NoAvailableSpacesException_message_when_manager_asked_to_fetch_given_no_ticket_to_boy() {
        //Given
        normalParkingBoy.setParkingLots(Arrays.asList(parkingLot1, parkingLot2));
        serviceManager.setParkingBoyList(Arrays.asList(normalParkingBoy, smartParkingBoy));
        IntStream.range(0, 4).forEach(cars -> {
            serviceManager.orderParkingBoyToPark(car, normalParkingBoy);
        });
        //then
        assertThrows(NoAvailableSpacesException.class, () -> {
            // when
            serviceManager.orderParkingBoyToPark(car, normalParkingBoy);
        });
    }

}

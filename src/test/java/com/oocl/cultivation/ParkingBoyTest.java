package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_park_given_car_to_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        // when
        ParkingTicket ticket = parkingBoy.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetched_given_valid_ticket_to_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);
        // when
        Car returnCar = parkingBoy.fetch(parkingTicket);
        //then
        assertSame(car, returnCar);
    }

    @Test
    void should_return_two_car_when_fetched_given_two_valid_ticket_to_boy() {
        //given
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        // when
        Car returnCar1 = parkingBoy.fetch(parkingTicket1);
        Car returnCar2 = parkingBoy.fetch(parkingTicket2);
        //then
        assertSame(car1, returnCar1);
        assertSame(car2, returnCar2);

    }

    @Test
    void should_return_UnrecognizedParkingTicket_error_message_when_fetched_given_invalid_ticket_to_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket validTicket = parkingBoy.park(car);
        // when
        Car returnCar = parkingBoy.fetch(validTicket);
        //then
        assertThrows(UnrecognizedParkingTicket.class, () -> {
            parkingBoy.fetch(validTicket);
        });
        assertSame(car, returnCar);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_error_message_when_fetched_given_reused_ticket_to_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket validTicket = parkingBoy.park(car);
        // when
        Car returnCar = parkingBoy.fetch(validTicket);
        //then
        assertThrows(UnrecognizedParkingTicket.class, () -> {
            parkingBoy.fetch(validTicket);
        });
        assertSame(car, returnCar);
    }

    @Test
    void should_return_NoTicketException_error_message_when_fetched_given_no_ticket_to_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket validTicket = parkingBoy.park(car);
        // when
        Car returnCar = parkingBoy.fetch(validTicket);
        //then
        assertThrows(NoTicketException.class, () -> {
            parkingBoy.fetch(null);
        });
        assertSame(car, returnCar);
    }

    @Test
    void should_return_NoAvailableSpacesException_error_message_when_fetched_given_car_to_boy_and_full() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        IntStream.range(0, 9).forEach(cars -> {
            Car carsNew = new Car();
            parkingBoy.park(carsNew);
        });
        // when
        parkingBoy.park(car);
        //then
        assertThrows(NoAvailableSpacesException.class, () -> {
            parkingBoy.park(car);
        });
    }

    @Test
    void should_return_number_of_parked_cars_when_park_given_two_parking_lot_with_not_clever_boy() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(5);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy.setListParkingLots(parkingLots);
        // when
        IntStream.range(0, 3).forEach(cars -> {
            Car carsNew = new Car();
            parkingBoy.park(carsNew);
        });
        int actual1 = parkingLot1.getParkedCars().size();
        int actual2 = parkingLot2.getParkedCars().size();
        //then
        assertEquals(1, actual1);
        assertEquals(2, actual2);
    }
}

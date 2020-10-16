package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

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
        assertSame(car1,returnCar1);
        assertSame(car2,returnCar2);

    }
    @Test
    void should_return_UnrecognizedParkingTicket_error_message_when_fetched_given_invalid_ticket_to_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket validTicket = parkingBoy.park(car);
        ParkingTicket invalidTicket = new ParkingTicket();
        // when
        Car returnCar = parkingBoy.fetch(validTicket);
        //then
        assertThrows(UnrecognizedParkingTicket.class,()-> {
            parkingBoy.fetch(invalidTicket);
        });
        assertSame(car,returnCar);
    }
}

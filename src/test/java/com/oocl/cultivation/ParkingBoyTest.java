package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private Car car;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        //given
        car = new Car();
        parkingBoy = new ParkingBoy(new ParkingLot());
    }

    private ParkingTicket parkACar() {
        return parkingBoy.park(car);
    }

    private Car fetchCar(ParkingTicket ticket) {
        return parkingBoy.fetch(ticket);
    }

    @Test
    void should_return_parking_ticket_when_park_given_car_to_boy() {
        // when & then
        assertNotNull(parkACar());
    }

    @Test
    void should_return_car_when_fetched_given_valid_ticket_to_boy() {
        //when & then
        assertSame(car, fetchCar(parkACar()));
    }

    @Test
    void should_return_two_car_when_fetched_given_two_valid_ticket_to_boy() {
        //given
        Car car1 = car;
        Car car2 = car;
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);
        // when
        Car returnCar1 = fetchCar(parkingTicket1);
        Car returnCar2 = fetchCar(parkingTicket2);
        //then
        assertSame(car1, returnCar1);
        assertSame(car2, returnCar2);

    }

    @Test
    void should_return_UnrecognizedParkingTicket_error_message_when_fetched_given_invalid_ticket_to_boy() {
        //given
        ParkingTicket validTicket = parkACar();
        ParkingTicket invalidTicket = new ParkingTicket();
        // when
        Car returnCar = fetchCar(validTicket);
        //then
        UnrecognizedParkingTicket unrecognizedParkingTicket = assertThrows(UnrecognizedParkingTicket.class, () ->
                fetchCar(invalidTicket));
        assertEquals("Unrecognized Parking Ticket", unrecognizedParkingTicket.getMessage());
        assertSame(car, returnCar);
    }

    @Test
    void should_return_UnrecognizedParkingTicket_error_message_when_fetched_given_reused_ticket_to_boy() {
        //given
        ParkingTicket validTicket = parkACar();
        // when
        Car returnCar = fetchCar(validTicket);
        //then
        UnrecognizedParkingTicket unrecognizedTicket = assertThrows(UnrecognizedParkingTicket.class, ()
                -> fetchCar(validTicket));
        assertEquals("Unrecognized Parking Ticket", unrecognizedTicket.getMessage());
        assertSame(car, returnCar);
    }

    @Test
    void should_return_NoTicketException_error_message_when_fetched_given_no_ticket_to_boy() {
        // when
        Car returnCar = fetchCar(parkACar());
        //then
        NoTicketException noTicketException = assertThrows(NoTicketException.class, () -> fetchCar(null));
        assertEquals("Please Provide Your Parking Ticket", noTicketException.getMessage());
        assertSame(car, returnCar);
    }

    @Test
    void should_return_NoAvailableSpacesException_error_message_when_fetched_given_car_to_boy_and_full() {
        //given
        IntStream.range(0, 9).forEach(cars -> {
            parkingBoy.park(car);
        });
        // when
        parkACar();
        //then
        NoAvailableSpacesException noAvailableSpacesException =
                assertThrows(NoAvailableSpacesException.class, this::parkACar);
        assertEquals("Not Enough Position", noAvailableSpacesException.getMessage());
    }


    @Test
    void should_return_number_of_parked_cars_when_park_given_two_parking_lot_with_not_clever_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(5);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
        // when
        IntStream.range(0, 3).forEach(cars -> {
            parkingBoy.park(car);
        });
        int actual1 = parkingLot1.getParkedCars().size();
        int actual2 = parkingLot2.getParkedCars().size();
        //then
        assertEquals(1, actual1);
        assertEquals(2, actual2);
    }

}

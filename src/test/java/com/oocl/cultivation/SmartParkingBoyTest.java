package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartParkingBoyTest {

    private Car car;

    @BeforeEach
    void setUp() {
        //given
        car = new Car();
    }

    private void parkCars(SmartParkingBoy smartParkingBoy) {
        IntStream.range(0, 3).forEach(cars -> {
            smartParkingBoy.park(car);
        });
    }

    @Test
    void should_return_number_of_parked_cars_when_parked_given_two_parking_lot_to_smart_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(15);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        // when
        parkCars(smartParkingBoy);
        int actual1 = parkingLot1.getParkedCars().size();
        int actual2 = parkingLot2.getParkedCars().size();
        //then
        assertEquals(0, actual1);
        assertEquals(3, actual2);
    }


    @Test
    void should_return_NoAvailableSpacesException_error_message_when_park_given_car_to_boy_and_full() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        // when
        parkCars(smartParkingBoy);
        //then
        NoAvailableSpacesException noAvailableSpacesException = assertThrows(NoAvailableSpacesException.class, () -> {
            smartParkingBoy.park(car);
        });
        assertEquals("Not Enough Position", noAvailableSpacesException.getMessage());
    }

}

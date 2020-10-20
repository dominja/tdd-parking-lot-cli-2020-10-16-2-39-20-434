package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuperSmartParkingBoyTest {
    @Test
    void should_return_number_of_parked_cars_when_parked_given_two_parking_lot_to_super_smart_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(15);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot1, parkingLot2);
        // when
        IntStream.range(0, 7).forEach(cars -> {
            Car carsNew = new Car();
            superSmartParkingBoy.park(carsNew);
        });
        int actual1 = parkingLot1.getParkedCars().size();
        int actual2 = parkingLot2.getParkedCars().size();
        //then
        assertEquals(1, actual1);
        assertEquals(6, actual2);
    }
}

package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartParkingBoyTest {
    @Test
    void should_return_number_of_parked_cars_when_parked_give_two_parking_lot_to_smart_boy() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());

        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot(15);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        smartParkingBoy.setListParkingLots(parkingLots);
        // when
        IntStream.range(0, 3).forEach(cars -> {
            Car carsNew = new Car();
            smartParkingBoy.park(carsNew);
        });
        int actual1 = parkingLot1.getParkedCars().size();
        int actual2 = parkingLot2.getParkedCars().size();
        //then
        assertEquals(0, actual1);
        assertEquals(3, actual2);
    }
}

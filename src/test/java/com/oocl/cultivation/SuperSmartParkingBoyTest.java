package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuperSmartParkingBoyTest {
    @Test
    void should_return_number_of_parked_cars_when_parked_give_two_parking_lot_to_super_smart_boy() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());

        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(15);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        superSmartParkingBoy.setListParkingLots(parkingLots);
        // when
        IntStream.range(0, 7).forEach(cars -> {
            Car carsNew = new Car();
            superSmartParkingBoy.park(carsNew);
        });
        int actual1 = parkingLot1.getParkedCars().size();
        int actual2 = parkingLot2.getParkedCars().size();
        //then
        assertEquals(2, actual1);
        assertEquals(5, actual2);
    }
}

package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {
    @Test
    void should_return_NoAvailableSpacesException_error_msg_when_parking_lot_is_full() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        ParkingLot parkingLot1 = new ParkingLot(1, 0);
        ParkingLot parkingLot2 = new ParkingLot(6, 0);
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        parkingBoy.setListParkingLots(parkingLots);
        // when
        IntStream.range(0, 7).forEach(cars -> {
            Car carsNew = new Car();
            parkingBoy.park(carsNew);
        });
        //then
        assertThrows(NoAvailableSpacesException.class, () -> {
            parkingBoy.park(car);
        });
    }

}
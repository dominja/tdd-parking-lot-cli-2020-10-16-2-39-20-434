package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {
    @Test
    void should_return_NoAvailableSpacesException_error_msg_when_parking_lot_is_full_given_car() {
        //given
        Car car = new Car();

        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(6);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
        // when
        IntStream.range(0, 7).forEach(cars -> {
            Car carsNew = new Car();
            parkingBoy.park(carsNew);
        });
        //then
        NoAvailableSpacesException noAvailableSpacesException = assertThrows(NoAvailableSpacesException.class, () -> {
            parkingBoy.park(car);
        });
        assertEquals("Not Enough Position", noAvailableSpacesException.getMessage());
    }

}
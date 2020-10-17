package com.oocl.cultivation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ServiceManagerTest {
    @Test
    void should_return_list_of_parking_boys_to_management_list_manage_parking_boys() {
        //Given
        List<ParkingBoy> parkingBoys;
        ServiceManager serviceManager = new ServiceManager();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        List<ParkingBoy> listParkingBoy = Arrays.asList(parkingBoy, smartParkingBoy);
        //When
        parkingBoys=serviceManager.getParkingBoyList(listParkingBoy);
        // Then
        Assertions.assertNotNull(parkingBoys);
    }
}

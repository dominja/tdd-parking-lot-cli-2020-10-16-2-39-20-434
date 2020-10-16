package com.oocl.cultivation;

public class UnrecognizedParkingTicket extends RuntimeException {
    UnrecognizedParkingTicket(String message) {
        super(message);
    }
}
package com.oocl.cultivation;

public class NoTicketException extends RuntimeException {
    NoTicketException(String message) {
        super(message);
    }
}

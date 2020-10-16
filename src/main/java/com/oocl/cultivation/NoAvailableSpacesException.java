package com.oocl.cultivation;

public class NoAvailableSpacesException extends RuntimeException {
    NoAvailableSpacesException(String message) {
        super(message);
    }
}

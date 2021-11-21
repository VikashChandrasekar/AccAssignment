package com.acc.airport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AirportServiceException extends Exception {

    public AirportServiceException(String message) {
        super(message);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }
}

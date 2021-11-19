package com.acc.airport.validation;

import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;

import java.util.List;

public class Validator {


    public static void validate(List<Result> results) throws AirportServiceException {
        if (results == null || results.size() == 0) {
            throw new AirportServiceException("This country airport has no runways");
        }
    }

}

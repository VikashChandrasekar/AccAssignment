package com.acc.airport.validation;

import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;

import java.security.InvalidParameterException;
import java.util.List;

public class Validator {

    public static void validateParam(String countryName, String countryCode) {
        if (countryName == null && countryCode == null) {
            throw new InvalidParameterException("Please give countryName or countryCode");
        }
    }

    public static void validate(List<Result> results) throws AirportServiceException {
        if (results == null || results.size() == 0) {
            throw new AirportServiceException("This country airport has no runways");
        }
    }

}

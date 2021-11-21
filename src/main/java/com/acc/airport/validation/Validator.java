package com.acc.airport.validation;

import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;
import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;
import java.util.List;

public class Validator {

    public static void validateCountryCode(String countryCode) {
        if (StringUtils.isBlank(countryCode) || countryCode.length() != 2) {
            throw new InvalidParameterException("Country code should be 2 letters");
        }
    }

    public static void validateCountryName(String countryName) {
        if (StringUtils.isBlank(countryName)) {
            throw new InvalidParameterException("Please give the country name");
        }
    }

    public static void validate(List<Result> results) throws AirportServiceException {
        if (results == null || results.size() == 0) {
            throw new AirportServiceException("This country airport has no runways");
        }
    }

}

package com.acc.airport.validation;

import com.acc.airport.exception.AirportServiceException;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;
import org.springframework.web.server.ResponseStatusException;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class ValidatorTest {

    private Validator validator;

    @Before
    public void setup() {
        validator = PowerMock.createMock(Validator.class);
    }

    @Test(expected = InvalidParameterException.class)
    public void validateParamWithCountryCodeTest() {
        validator.validateCountryCode(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void validateParamWithCountryNameTest() {
        validator.validateCountryName(null);
    }


    @Test(expected = ResponseStatusException.class)
    public void validateTest() throws AirportServiceException {
        validator.validate(new ArrayList<>());
    }
}

package com.acc.airport.config;

import com.acc.airport.exception.AirportServiceException;
import com.acc.airport.service.AirportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

public class DataConfigTest {

    private AirportService mockAirportService;

    @Before
    public void setUp() {
        mockAirportService = PowerMock.createMock(AirportService.class);
    }

    @Test
    public void mapDataConfigTest() throws AirportServiceException {
        get().mapDataValues();
        Assert.assertNotNull(mockAirportService.countries);
        Assert.assertNotNull(mockAirportService.airports);
        Assert.assertNotNull(mockAirportService.runways);
        Assert.assertEquals("NL", mockAirportService.countries.get(0).getCode());
        Assert.assertEquals("Netherlands", mockAirportService.countries.get(0).getName());
        Assert.assertEquals("Amsterdam Airport Schiphol", mockAirportService.airports.get(0).getName());
        Assert.assertEquals(2513, mockAirportService.runways.get(0).getAirport_ref());
    }

    private DataConfig get() {
        return new DataConfig(mockAirportService);
    }
}


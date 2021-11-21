package com.acc.airport.service;

import com.acc.airport.Helper.TestStub;
import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

public class AirportServiceTest {

    private AirportService airportService;

    @Before
    public void setUp() {
        airportService = new AirportService(TestStub.createCountry(), TestStub.createAirport(), TestStub.createRunway());
    }

    @Test
    public void getRunwayByCountryWithCountryNameTest() throws AirportServiceException {
        List<Result> resultList = airportService.getRunwayByCountry(null,"Netherlands");
        Assert.assertNotNull(resultList);
        Assert.assertEquals("Netherlands", resultList.get(0).getCountryName());
        Assert.assertEquals("NL", resultList.get(0).getCountryCode());
        Assert.assertEquals("Amsterdam Airport Schiphol", resultList.get(0).getAirportName());
        Assert.assertEquals("Closed", resultList.get(0).getClosed());
        Assert.assertEquals("Yes", resultList.get(0).getLighted());
    }

    @Test
    public void getRunwayByCountryWithCountryCodeTest() throws AirportServiceException {
        List<Result> resultList = airportService.getRunwayByCountry("IN", null);
        Assert.assertNotNull(resultList);
        Assert.assertEquals("India", resultList.get(0).getCountryName());
        Assert.assertEquals("IN", resultList.get(0).getCountryCode());
        Assert.assertEquals("Chennai International Airport", resultList.get(0).getAirportName());
        Assert.assertEquals("Open", resultList.get(0).getClosed());
        Assert.assertEquals("No", resultList.get(0).getLighted());
    }

    @Test(expected = ResponseStatusException.class)
    public void getRunwayByCountryWithEmptyValuesTest() throws AirportServiceException {
        airportService.getRunwayByCountry(null, null);
    }

    @Test
    public void getTopCountryWithMoreAirportsTest() {
        Map<String, Long> map = airportService.getTopCountryWithMoreAirports();
        Assert.assertNotNull(map);
        Assert.assertEquals(2, Long.parseLong(String.valueOf(map.get("Netherlands"))));
        Assert.assertEquals(1, Long.parseLong(String.valueOf(map.get("India"))));
    }

}

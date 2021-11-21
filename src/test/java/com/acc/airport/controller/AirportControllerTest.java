package com.acc.airport.controller;

import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;
import com.acc.airport.service.AirportService;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportControllerTest {

    private AirportService mockAirportService;
    private AirportController airportController;

    @Before
    public void setUp() {
        mockAirportService = PowerMock.createMock(AirportService.class);
        airportController = new AirportController(mockAirportService);
    }

    @Test
    public void getRunwaysWithCountryNameTest() throws AirportServiceException {
        EasyMock.expect(mockAirportService.getRunwayByCountry(null, "Netherlands")).andReturn(createResult());
        PowerMock.replayAll();
        List<Result> resultList = airportController.getRunwaysByCountryName("Netherlands");
        PowerMock.verifyAll();
        Assert.assertNotNull(resultList);
    }

    @Test
    public void getRunwaysWithCountryCodeTest() throws AirportServiceException {
        EasyMock.expect(mockAirportService.getRunwayByCountry("NL", null)).andReturn(createResult());
        PowerMock.replayAll();
        List<Result> resultList = airportController.getRunwaysByCountryCode("NL");
        PowerMock.verifyAll();
        Assert.assertNotNull(resultList);
    }

    @Test(expected = InvalidParameterException.class)
    public void getRunwaysWithCountryCodeNotFoundTest() throws AirportServiceException {
        airportController.getRunwaysByCountryCode(null);
    }

    @Test(expected = InvalidParameterException.class)
    public void getRunwaysWithCountryNameNotFoundTest() throws AirportServiceException {
        airportController.getRunwaysByCountryName(null);
    }

    @Test
    public void getCountriesWithMoreAirportTest() {
        EasyMock.expect(mockAirportService.getTopCountryWithMoreAirports()).andReturn(createTop10Countries());
        PowerMock.replayAll();
        Map<String, Long> top10Countries = airportController.getTopCountryWithMoreAirports();
        Assert.assertNotNull(top10Countries);
    }

    private List<Result> createResult() {
        List<Result> results = new ArrayList<>();
        Result result = Result.builder()
                              .countryCode("NL")
            .countryName("Netherlands")
            .airportName("Amsterdam Airport Schiphol")
            .closed("Open")
            .lighted("Yes").build();
        results.add(result);
        return results;
    }

    private Map<String, Long> createTop10Countries() {
        Map<String, Long> countryMap = new HashMap<>();
        countryMap.put("America", 200L);
        countryMap.put("Germany", 150L);
        countryMap.put("Belgium", 125L);
        countryMap.put("England", 110L);
        countryMap.put("Chile", 100L);
        countryMap.put("China", 85L);
        countryMap.put("Canada", 70L);
        countryMap.put("Japan", 75L);
        countryMap.put("Spain", 60L);
        countryMap.put("India", 40L);
        return countryMap;
    }

}

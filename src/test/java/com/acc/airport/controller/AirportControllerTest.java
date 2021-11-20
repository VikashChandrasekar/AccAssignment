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

    @Before
    public void setUp() {
        mockAirportService = PowerMock.createMock(AirportService.class);
    }

    @Test
    public void getRunwaysWithCountryNameTest() throws AirportServiceException {
        EasyMock.expect(mockAirportService.getRunwayByCountry("Netherlands", null)).andReturn(createResult());
        PowerMock.replayAll();
        List<Result> resultList = get().getRunways("Netherlands", null);
        PowerMock.verifyAll();
        Assert.assertNotNull(resultList);
    }

    @Test
    public void getRunwaysWithCountryCodeTest() throws AirportServiceException {
        EasyMock.expect(mockAirportService.getRunwayByCountry( null,"NL")).andReturn(createResult());
        PowerMock.replayAll();
        List<Result> resultList = get().getRunways(null, "NL");
        PowerMock.verifyAll();
        Assert.assertNotNull(resultList);
    }

    @Test(expected = InvalidParameterException.class)
    public void getRunwaysWithRunwayNotFoundTest() throws AirportServiceException {
        EasyMock.expect(mockAirportService.getRunwayByCountry( null,null)).andReturn(null);
        get().getRunways(null, null);
    }

    @Test
    public void getCountriesWithMoreAirportTest() {
        EasyMock.expect(mockAirportService.getTopCountryWithMoreAirports()).andReturn(createTop10Countries());
        PowerMock.replayAll();
        Map<String, Long> top10Countries = get().getTopCountryWithMoreAirports();
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
        Map<String, Long> map = new HashMap<>();
        map.put("America", 200L);
        map.put("Germany", 150L);
        map.put("Belgium", 125L);
        map.put("England", 110L);
        map.put("Chile", 100L);
        map.put("China", 85L);
        map.put("Canada", 70L);
        map.put("Japan", 75L);
        map.put("Spain", 60L);
        map.put("India", 40L);
        return map;
    }

    public AirportController get() {
        return new AirportController(mockAirportService);
    }
}

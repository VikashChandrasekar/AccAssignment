package com.acc.airport.controller;

import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;
import com.acc.airport.service.AirportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportController.class);

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }


    @GetMapping(value = "/runway")
    public List<Result> getRunways(@RequestParam(value = "countryName", required = false) final String countryName,
                                   @RequestParam(value = "countryCode", required = false) final String countryCode)
        throws AirportServiceException {
        LOGGER.info("Retrieving the runway information");
        return airportService.getRunwayByCountry(countryName, countryCode);
    }

    @GetMapping(value = "/topCountryWithMoreAirports")
    public Map<String, Long> getTopCountryWithMoreAirports() {
        LOGGER.info("Retrieving the top 10 countries with more airports");
        return airportService.getTopCountryWithMoreAirports();
    }

}

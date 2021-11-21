package com.acc.airport.controller;

import com.acc.airport.domain.Result;
import com.acc.airport.exception.AirportServiceException;
import com.acc.airport.service.AirportService;

import com.acc.airport.validation.Validator;
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

    /**This endpoint will return list contains the country, airport, runways information for the given
     * country code.
     *
     * @param countryCode the country code
     * @return the list of Result
     * @throws AirportServiceException
     */
    @GetMapping(value = "/runwayWithCode")
    public List<Result> getRunwaysByCountryCode(@RequestParam(value = "countryCode") final String countryCode)
        throws AirportServiceException {
        LOGGER.info("Retrieving the runway information from country Code : " +countryCode);
        Validator.validateCountryCode(countryCode);
        return airportService.getRunwayByCountry(countryCode, null);
    }

    /**This endpoint will return list contains the country, airport, runways information for the given
     * country name.
     *
     * @param countryName the country name
     * @return the list of Result
     * @throws AirportServiceException
     */
    @GetMapping(value = "/runwayWithName")
    public List<Result> getRunwaysByCountryName(@RequestParam(value = "countryName") final String countryName)
        throws AirportServiceException {
        LOGGER.info("Retrieving the runway information from country name : " + countryName);
        Validator.validateCountryName(countryName);
        return airportService.getRunwayByCountry(null, countryName);
    }

    /**This end point returns the top 10 countries with more airport.
     *
     * @return map of <String, Long>
     */
    @GetMapping(value = "/topCountryWithMoreAirports")
    public Map<String, Long> getTopCountryWithMoreAirports() {
        LOGGER.info("Retrieving the top 10 countries with more airports");
        return airportService.getTopCountryWithMoreAirports();
    }

}

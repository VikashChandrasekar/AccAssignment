package com.acc.airport.service;

import com.acc.airport.domain.Airport;
import com.acc.airport.domain.Country;
import com.acc.airport.domain.Result;
import com.acc.airport.domain.Runway;
import com.acc.airport.exception.AirportServiceException;
import com.acc.airport.validation.Validator;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This AirportService is a service class
 *
 * @author Vikash Chandrasekar
 * @version 1.0
 * @since 2021-11-13
 */

@Service
public class AirportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportService.class);

    public List<Country> countries;
    public List<Airport> airports;
    public List<Runway> runways;

    public AirportService(List<Country> countries, List<Airport> airports, List<Runway> runways) {
        this.countries = countries;
        this.airports = airports;
        this.runways = runways;
    }

    /**
     *
     * @param countryName the country name
     * @param countryCode the country code
     * @return List of Runways
     * @throws AirportServiceException the exception
     */
    public List<Result> getRunwayByCountry(String countryName, String countryCode) throws AirportServiceException {
        Set<Country> countrySet = getCountries(countryName, countryCode);
        List<Result> results = getResults(countrySet);
        Validator.validate(results);
        return results;
    }

    /**
     *
     * @param countryName the country name
     * @param countryCode the country code
     * @return list of country
     */
    private Set<Country> getCountries(String countryName, String countryCode) {
        LOGGER.info("Fetching the country details");
        Set<Country> countrySet = new HashSet<>();
        countries.forEach(entry -> {
            if (StringUtils.isNotBlank(countryCode) && countryCode.equalsIgnoreCase(entry.getCode())) {
                countrySet.add(entry);
            }
            if (StringUtils.isNotBlank(countryName) && entry.getName().toUpperCase()
                                                            .contains(countryName.toUpperCase())) {
                countrySet.add(entry);
            }
        });
        return countrySet;
    }

    /**
     *
     * @param countrySet set of country.
     * @return Result - list of countries with airport and runway information.
     */
    private List<Result> getResults(Set<Country> countrySet) {
        LOGGER.info("Mapping the airports and runways");
        List<Result> results = new ArrayList<>();
        countrySet.forEach(country -> airports.forEach(airport -> {
            if (country.getCode().equalsIgnoreCase(airport.getIso_country())) {
                runways.forEach(runway -> {
                    if (airport.getId() == runway.getAirport_ref()) {
                        Result result = Result.builder()
                            .countryCode(country.getCode())
                            .countryName(country.getName())
                            .airportName(airport.getName())
                            .airportIdent(airport.getIdent())
                            .lengthFt(runway.getLength_ft())
                            .widthFt(runway.getWidth_ft())
                            .surface(runway.getSurface())
                            .lighted(runway.getLighted() == 0 ? "No" : "Yes")
                            .closed(runway.getClosed() == 0 ? "Closed" : "Open").build();
                        results.add(result);
                    }
                });
            }
        }));
        return results;
    }

    /**
     *
     * @return the Map, contains list of 10 countries with highest number of airports
     */
    public Map<String, Long> getTopCountryWithMoreAirports() {
        Map<String, Long> countriesWithNoOfAirportMap = new HashMap<>();
        countries.forEach(country -> {
            long noOfAirport = 0L;
            for (Airport airport : airports) {
                if(country.getCode().equalsIgnoreCase(airport.getIso_country())) {
                    noOfAirport = noOfAirport + 1;
                    countriesWithNoOfAirportMap.put(country.getName(), noOfAirport);
                }
            }
        });
        LOGGER.info("Sorting of the airports");
        return countriesWithNoOfAirportMap.entrySet().stream()
               .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
               .limit(10)
               .collect(Collectors.toMap(
                   Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}

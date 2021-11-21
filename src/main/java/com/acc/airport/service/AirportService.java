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

    /**This method iterates the country list for the given param, then iterate the airport and runways list using
     * country code or name and will form a list of Result which contains country, airport and runway information for
     * the given param.
     *
     * @param countryCode the country code
     * @param countryName the country name
     * @return List of Runways
     * @throws AirportServiceException the exception
     */
    public List<Result> getRunwayByCountry(String countryCode, String countryName) throws AirportServiceException {
        Set<Country> countrySet = getCountries(countryCode, countryName);
        List<Result> results = getResults(countrySet);
        Validator.validate(results);
        return results;
    }

    /**This method iterates the country list and fetch the correct country for the given param
     *
     * @param countryName the country name
     * @param countryCode the country code
     * @return list of country
     */
    private Set<Country> getCountries(String countryCode, String countryName) {
        LOGGER.info("Fetching the country details");
        Set<Country> countrySet = new HashSet<>();
        countries.forEach(entry -> {
            if ((StringUtils.isNotBlank(countryCode) && countryCode.equalsIgnoreCase(entry.getCode())) ||
                (StringUtils.isNotBlank(countryName) && entry.getName().toUpperCase().contains(countryName.toUpperCase()))) {
                countrySet.add(entry);
            }
        });
        return countrySet;
    }

    /**This method forms a list of Result which contains country, airport and runways informations for a particular
     * country code or name.
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

    /**This method maps the top 10 countries with the more number of airports.
     *
     * @return the Map of <String, Long>
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

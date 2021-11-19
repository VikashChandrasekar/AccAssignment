package com.acc.airport.config;

import com.acc.airport.domain.Airport;
import com.acc.airport.domain.Country;
import com.acc.airport.domain.Runway;
import com.acc.airport.exception.AirportServiceException;
import com.acc.airport.service.AirportService;

import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.List;

@Configuration
public class DataConfig {

    private final AirportService airportService;

    @Autowired
    public DataConfig(AirportService airportService) {
        this.airportService = airportService;
    }

    @Bean
    public void mapCountryValues() throws AirportServiceException {
        try {
            File countryFile = ResourceUtils.getFile("classpath:countries.csv");
            File airportFile = ResourceUtils.getFile("classpath:airports.csv");
            File runwayFile = ResourceUtils.getFile("classpath:runways.csv");

            List<Country> countries = new CsvToBeanBuilder(new FileReader(countryFile.getAbsolutePath()))
                .withType(Country.class).build().parse();
            airportService.countries = countries;

            List<Airport> airports = new CsvToBeanBuilder(new FileReader(airportFile.getAbsolutePath()))
                .withType(Airport.class).build().parse();
            airportService.airports = airports;

            List<Runway> runways = new CsvToBeanBuilder(new FileReader(runwayFile.getAbsolutePath()))
                .withType(Runway.class).build().parse();
            airportService.runways = runways;

        } catch (FileNotFoundException e) {
            throw new AirportServiceException(e.getMessage());
        }
    }


}

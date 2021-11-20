package com.acc.airport.Helper;

import com.acc.airport.domain.Airport;
import com.acc.airport.domain.Country;
import com.acc.airport.domain.Runway;

import java.util.ArrayList;
import java.util.List;

public class TestStub {

    public static List<Country> createCountry() {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setCode("NL");
        country.setName("Netherlands");
        countries.add(country);
        Country country1 = new Country();
        country1.setCode("IN");
        country1.setName("India");
        countries.add(country1);
        return countries;
    }

    public static List<Airport> createAirport() {
        List<Airport> airports = new ArrayList<>();
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Amsterdam Airport Schiphol");
        airport.setIdent("EHAM");
        airport.setIso_country("NL");
        airports.add(airport);
        Airport airport1 = new Airport();
        airport1.setId(2L);
        airport1.setName("Maastricht Aachen Airport");
        airport1.setIdent("EHAH");
        airport1.setIso_country("NL");
        airports.add(airport1);
        Airport airport2 = new Airport();
        airport2.setId(3L);
        airport2.setName("Chennai International Airport");
        airport2.setIdent("IN-0006");
        airport2.setIso_country("IN");
        airports.add(airport2);
        return airports;
    }

    public static List<Runway> createRunway() {
        List<Runway> runways = new ArrayList<>();
        Runway runway = new Runway();
        runway.setId(1L);
        runway.setAirport_ref(1L);
        runway.setAirport_ident("EHAM");
        runway.setLength_ft("310");
        runway.setWidth_ft("100");
        runway.setSurface("Hard");
        runway.setLighted(1);
        runway.setClosed(0);
        runways.add(runway);
        Runway runway1 = new Runway();
        runway1.setId(2L);
        runway1.setAirport_ref(3L);
        runway1.setAirport_ident("IN-0006");
        runway1.setLength_ft("210");
        runway1.setWidth_ft("90");
        runway1.setSurface("Grass");
        runway1.setLighted(0);
        runway1.setClosed(1);
        runways.add(runway1);
        return runways;
    }
}

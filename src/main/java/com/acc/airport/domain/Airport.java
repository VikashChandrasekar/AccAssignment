package com.acc.airport.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;

@Getter
public class Airport {

    @CsvBindByName
    private long id;

    @CsvBindByName
    private String ident;

    @CsvBindByName
    private String type;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String latitude_deg;

    @CsvBindByName
    private String longitude_deg;

    @CsvBindByName
    private String elevation_ft;

    @CsvBindByName
    private String continent;

    @CsvBindByName
    private String iso_country;

    @CsvBindByName
    private String iso_region;

    @CsvBindByName
    private String municipality;

    @CsvBindByName
    private String scheduled_service;

    @CsvBindByName
    private String gps_code;

    @CsvBindByName
    private String iata_code;

    @CsvBindByName
    private String local_code;

    @CsvBindByName
    private String home_link;

    @CsvBindByName
    private String wikipedia_link;

    @CsvBindByName
    private String keywords;

}

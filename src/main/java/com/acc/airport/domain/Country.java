package com.acc.airport.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {

    @CsvBindByName
    private long id;

    @CsvBindByName
    private String code;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String continent;

    @CsvBindByName
    private String wikipedia_link;

    @CsvBindByName
    private String keywords;

}

package com.acc.airport.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Result {

    /**
     *  The country name
     */
    private final String countryName;

    /**
     * The country code
     */
    private final String countryCode;

    /**
     * The airport name
     */
    private final String airportName;

    /**
     * The airport identifier
     */
    private final String airportIdent;

    /**
     * The runway length feet
     */
    private final String lengthFt;

    /**
     * The runway width feet
     */
    private final String widthFt;

    /**
     * The runway surface
     */
    private final String surface;

    /**
     * Runway lighted - Yes or No
     */
    private final String lighted;

    /**
     * Runway closed - Closed or Open
     */
    private final String closed;

}

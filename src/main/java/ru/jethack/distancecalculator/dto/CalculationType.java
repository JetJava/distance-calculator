package ru.jethack.distancecalculator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum CalculationType {
    CROWFLIGHT, DISTANCE_MATRIX, ALL;
}

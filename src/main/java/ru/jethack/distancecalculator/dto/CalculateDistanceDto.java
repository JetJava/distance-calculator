package ru.jethack.distancecalculator.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculateDistanceDto {
    private CalculationType calculationType;
    @JsonProperty("fromCity")
    private List<String> fromCityNameList;
    @JsonProperty("toCity")
    private List<String> toCityNameList;

    public CalculateDistanceDto() {
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(CalculationType calculationType) {
        this.calculationType = calculationType;
    }

    public List<String> getFromCityNameList() {
        return fromCityNameList;
    }

    public void setFromCityNameList(List<String> fromCityNameList) {
        this.fromCityNameList = fromCityNameList;
    }

    public List<String> getToCityNameList() {
        return toCityNameList;
    }

    public void setToCityNameList(List<String> toCityNameList) {
        this.toCityNameList = toCityNameList;
    }
}

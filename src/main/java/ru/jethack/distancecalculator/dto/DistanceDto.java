package ru.jethack.distancecalculator.dto;

public class DistanceDto {
    private final String fromCityName;
    private final String toCityName;

    public CalculationType getCalculationType() {
        return calculationType;
    }

    private final CalculationType calculationType;
    private final double distance;

    public String getFromCityName() {
        return fromCityName;
    }

    public String getToCityName() {
        return toCityName;
    }

    public double getDistance() {
        return distance;
    }

    public DistanceDto(String fromCityName, String toCityName, CalculationType calculationType, double distance) {
        this.fromCityName = fromCityName;
        this.toCityName = toCityName;
        this.calculationType = calculationType;
        this.distance = distance;
    }
}

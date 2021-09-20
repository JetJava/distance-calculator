package ru.jethack.distancecalculator.dto;

import java.util.List;

public class DistancesDto {
    private final CalculationType calculationType;
    private final List<DistanceDto> distances;

    public DistancesDto(CalculationType calculationType, List<DistanceDto> distances) {
        this.calculationType = calculationType;
        this.distances = distances;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public List<DistanceDto> getDistances() {
        return distances;
    }
}

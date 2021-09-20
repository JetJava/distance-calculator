package ru.jethack.distancecalculator.service;

import ru.jethack.distancecalculator.dto.CalculateDistanceDto;

public interface CalculateDistanceService {
    Object calculateDistance(CalculateDistanceDto calculateDistanceDto);
}
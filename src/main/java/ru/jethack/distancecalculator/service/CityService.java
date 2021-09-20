package ru.jethack.distancecalculator.service;

import ru.jethack.distancecalculator.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> getAllCities();
}

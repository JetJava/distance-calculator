package ru.jethack.distancecalculator.service.implementation;

import org.springframework.stereotype.Service;
import ru.jethack.distancecalculator.dto.CalculateDistanceDto;
import ru.jethack.distancecalculator.dto.CalculationType;
import ru.jethack.distancecalculator.dto.DistanceDto;
import ru.jethack.distancecalculator.dto.DistancesDto;
import ru.jethack.distancecalculator.exception.CityNotFoundException;
import ru.jethack.distancecalculator.exception.DistanceNotFoundException;
import ru.jethack.distancecalculator.model.City;
import ru.jethack.distancecalculator.model.Distance;
import ru.jethack.distancecalculator.repository.CityRepository;
import ru.jethack.distancecalculator.repository.DistanceRepository;
import ru.jethack.distancecalculator.service.CalculateDistanceService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

@Service
public class CalculateDistanceServiceImpl implements CalculateDistanceService {

    public static final double AVERAGE_EARTH_RADIUS = 6371.032;

    final
    DistanceRepository distanceRepository;

    final
    CityRepository cityRepository;

    public CalculateDistanceServiceImpl(DistanceRepository distanceRepository, CityRepository cityRepository) {
        this.distanceRepository = distanceRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Object calculateDistance(CalculateDistanceDto calculateDistanceDto) {

        List<DistanceDto> distances;

        try {
            switch (calculateDistanceDto.getCalculationType()) {

                case CROWFLIGHT:
                    distances = calculateCrowflightDistance(calculateDistanceDto);
                    break;
                case DISTANCE_MATRIX:
                    distances = calculateDistanceByDistanceMatrix(calculateDistanceDto);
                    break;
                case ALL:
                    distances = calculateCrowflightDistance(calculateDistanceDto);
                    distances.addAll(calculateDistanceByDistanceMatrix(calculateDistanceDto));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + calculateDistanceDto.getCalculationType());
            }
        } catch (CityNotFoundException | DistanceNotFoundException e) {
            return e;
        }
        return new DistancesDto(calculateDistanceDto.getCalculationType(), distances);
    }

    private List<DistanceDto> calculateCrowflightDistance(CalculateDistanceDto calculateDistanceDto) throws CityNotFoundException {
        List<DistanceDto> result = new ArrayList<>();
        for (String fromCityName : calculateDistanceDto.getFromCityNameList()) {
            for (String toCityName : calculateDistanceDto.getToCityNameList()) {
                City fromCity = cityRepository.findByName(fromCityName);
                City toCity = cityRepository.findByName(toCityName);
                if (fromCity == null) throw new CityNotFoundException(fromCityName);
                if (toCity == null) throw new CityNotFoundException(toCityName);
                double lat1 = fromCity.getLatitude() * PI / 180;
                double lat2 = toCity.getLatitude() * PI / 180;
                double long1 = fromCity.getLongitude() * PI / 180;
                double long2 = toCity.getLongitude() * PI / 180;
                double distance = AVERAGE_EARTH_RADIUS * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(long2 - long1));
                result.add(new DistanceDto(fromCity.getName(), toCity.getName(), CalculationType.CROWFLIGHT, distance));
            }
        }
        return result;
    }

    private List<DistanceDto> calculateDistanceByDistanceMatrix(CalculateDistanceDto calculateDistanceDto) throws CityNotFoundException, DistanceNotFoundException {
        List<DistanceDto> result = new ArrayList<>();
        for (String fromCityName : calculateDistanceDto.getFromCityNameList()) {
            for (String toCityName : calculateDistanceDto.getToCityNameList()) {
                City fromCity = cityRepository.findByName(fromCityName);
                City toCity = cityRepository.findByName(toCityName);
                if (fromCity == null) throw new CityNotFoundException(fromCityName);
                if (toCity == null) throw new CityNotFoundException(toCityName);
                Distance distance = distanceRepository.findByFromCityAndToCity(fromCity, toCity);
                if (distance == null) throw new DistanceNotFoundException(fromCityName, toCityName);
                result.add(new DistanceDto(fromCityName, toCityName, CalculationType.DISTANCE_MATRIX, distance.getDistance()));
            }
        }
        return result;
    }
}

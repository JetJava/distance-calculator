package ru.jethack.distancecalculator.service.implementation;

import org.springframework.stereotype.Service;
import ru.jethack.distancecalculator.dto.CityDto;
import ru.jethack.distancecalculator.model.City;
import ru.jethack.distancecalculator.repository.CityRepository;
import ru.jethack.distancecalculator.service.CityService;

import java.util.LinkedList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    final
    CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        List<CityDto> cityDtoList = new LinkedList<>();
        for (City city : cities) {
            cityDtoList.add(new CityDto(city.getId(), city.getName()));
        }
        return cityDtoList;
    }
}

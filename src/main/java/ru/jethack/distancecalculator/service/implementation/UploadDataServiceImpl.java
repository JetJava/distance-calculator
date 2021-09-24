package ru.jethack.distancecalculator.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.jethack.distancecalculator.exception.CityNotFoundException;
import ru.jethack.distancecalculator.service.UploadDataService;
import ru.jethack.distancecalculator.xml.CityData;
import ru.jethack.distancecalculator.xml.DistanceData;
import ru.jethack.distancecalculator.xml.UploadData;
import ru.jethack.distancecalculator.model.City;
import ru.jethack.distancecalculator.model.Distance;
import ru.jethack.distancecalculator.repository.CityRepository;
import ru.jethack.distancecalculator.repository.DistanceRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
public class UploadDataServiceImpl implements UploadDataService {

    final
    DistanceRepository distanceRepository;

    final
    CityRepository cityRepository;

    public UploadDataServiceImpl(DistanceRepository distanceRepository, CityRepository cityRepository) {
        this.distanceRepository = distanceRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Object saveData(MultipartFile file) {
        List<City> cityList = new LinkedList<>();
        List<Distance> distanceList = new LinkedList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(UploadData.class);
            UploadData uploadData = (UploadData) context.createUnmarshaller().unmarshal(file.getInputStream());
            for (CityData cityData : uploadData.getCities()) {
                City city = cityRepository.findByName(cityData.getName());
                if (city == null) {
                    cityList.add(new City(cityData.getName(), cityData.getLatitude(), cityData.getLongitude()));
                } else {
                    city.setLatitude(cityData.getLatitude());
                    city.setLongitude(cityData.getLongitude());
                }
            }
            cityRepository.saveAll(cityList);

            for (DistanceData distanceData : uploadData.getDistances()) {
                City fromCity = cityRepository.findByName(distanceData.getFromCityName());
                City toCity = cityRepository.findByName(distanceData.getToCityName());
                if (fromCity == null) throw new CityNotFoundException(distanceData.getFromCityName());
                if (toCity == null) throw new CityNotFoundException(distanceData.getToCityName());
                Distance distance = distanceRepository.findByFromCityAndToCity(fromCity, toCity);
                if (distance == null) {
                    distanceList.add(new Distance(cityRepository.findByName(distanceData.getFromCityName()),
                            cityRepository.findByName(distanceData.getToCityName()),
                            distanceData.getDistance()));
                } else {
                    distance.setDistance(distanceData.getDistance());
                }
            }
            distanceRepository.saveAll(distanceList);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        } catch (CityNotFoundException e) {
            return e;
        }
        return null;
    }
}

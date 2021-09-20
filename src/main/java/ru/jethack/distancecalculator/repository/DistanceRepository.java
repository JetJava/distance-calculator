package ru.jethack.distancecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jethack.distancecalculator.model.City;
import ru.jethack.distancecalculator.model.Distance;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {
    Distance findByFromCityAndToCity(City fromCity, City toCity);
}
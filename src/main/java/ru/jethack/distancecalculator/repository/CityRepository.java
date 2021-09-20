package ru.jethack.distancecalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.jethack.distancecalculator.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}
package ru.jethack.distancecalculator.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jethack.distancecalculator.service.CityService;

@RestController
@RequestMapping("cities")
public class CityController {
    final
    CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCities() {
        return ResponseEntity.ok().body(cityService.getAllCities());
    }
}

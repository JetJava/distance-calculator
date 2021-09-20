package ru.jethack.distancecalculator.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jethack.distancecalculator.dto.CalculateDistanceDto;
import ru.jethack.distancecalculator.dto.DistancesDto;
import ru.jethack.distancecalculator.service.CalculateDistanceService;

@Controller
@RequestMapping("calculate-distance")
public class CalculateDistanceController {
    final CalculateDistanceService calculateDistanceService;

    public CalculateDistanceController(CalculateDistanceService calculateDistanceService) {
        this.calculateDistanceService = calculateDistanceService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> calculateDistance(@RequestBody CalculateDistanceDto calculateDistanceDto) {
        Object result = calculateDistanceService.calculateDistance(calculateDistanceDto);
        if (result instanceof DistancesDto) {
            return ResponseEntity.ok().body((DistancesDto) result);
        } else {
            return ResponseEntity.badRequest().body(((Exception) result).getMessage());
        }
    }
}

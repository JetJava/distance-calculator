package ru.jethack.distancecalculator.exception;

public class DistanceNotFoundException extends Exception {

    public DistanceNotFoundException(String fromCityName, String toCityName) {
        super(String.format("The distance between the cities of %s and %s is not found", fromCityName, toCityName));
    }
}
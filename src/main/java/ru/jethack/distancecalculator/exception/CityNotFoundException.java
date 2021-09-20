package ru.jethack.distancecalculator.exception;

public class CityNotFoundException extends Exception {

    public CityNotFoundException(String name) {
        super(String.format("The city with the name %s was not found", name));
    }
}
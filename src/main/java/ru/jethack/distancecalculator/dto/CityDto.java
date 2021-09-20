package ru.jethack.distancecalculator.dto;

public class CityDto {
    private final Long id;
    private final String name;

    public CityDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package ru.jethack.distancecalculator.model;

import javax.persistence.*;

@Table(name = "distance")
@Entity
public class Distance {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "from_city_id", nullable = false)
    private City fromCity;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "to_city_id", nullable = false)
    private City toCity;

    @Column(name = "distance")
    private Double distance;

    public Distance() {
    }

    public Distance(City fromCity, City toCity, Double distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City city) {
        this.fromCity = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
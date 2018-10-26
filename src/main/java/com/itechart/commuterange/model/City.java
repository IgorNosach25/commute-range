package com.itechart.commuterange.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class City {

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    @Id
    @GeneratedValue()
    protected int id;
    @Column(unique = true)
    private String cityName;

}

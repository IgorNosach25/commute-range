package com.itechart.commuterange.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class City {

    @Id
    @GeneratedValue()
    protected int id;
    private String name;
    private Map<City, Integer> distanceToNearbyCities;

    public String getName() {
        return name;
    }

    public Map<City, Integer> getDistanceToNearbyCities() {
        return distanceToNearbyCities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistanceToNearbyCities(Map<City, Integer> distanceToNearbyCities) {
        this.distanceToNearbyCities = distanceToNearbyCities;
    }
}

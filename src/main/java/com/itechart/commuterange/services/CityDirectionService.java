package com.itechart.commuterange.services;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CityDirectionService {

    Set<String> getReachableCitiesNames(String fromCity, int range);

    void saveDirectionBetweenCities(String from, String to, int range);

}

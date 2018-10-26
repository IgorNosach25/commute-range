package com.itechart.commuterange.services;

import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import com.itechart.commuterange.repositories.CitiesDirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class CityRangeDFSCounter implements CityRangeCounter {

    private CitiesDirectionRepository citiesDirectionRepository;

    @Autowired
    public CityRangeDFSCounter(CitiesDirectionRepository citiesDirectionRepository) {
        this.citiesDirectionRepository = citiesDirectionRepository;
    }

    @Override
    public Set<String> getReachableCities(City fromCity, int range) {
        return findReachableCities(fromCity, range, new HashSet<>());
    }

    private Set<String> findReachableCities(City fromCity, int range, Set<String> citiesNames) {
        Set<CitiesDirection> reachableCities = citiesDirectionRepository
                .findAllByFromAndDistanceIsLessThanEqual(fromCity, range);
        reachableCities.forEach((CitiesDirection direction) -> {
            String cityName = direction.getTo().getCityName();
            if (direction.getDistance() <= range && !citiesNames.contains(cityName)) {
                citiesNames.add(cityName);
                findReachableCities(direction.getTo(), range - direction.getDistance(), citiesNames);
            }
        });

        return citiesNames;
    }
}

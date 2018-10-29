package com.itechart.commuterange.services;

import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import com.itechart.commuterange.repositories.CitiesDirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        return findReachableCities(fromCity, range, new HashSet<>(), new HashMap<>());
    }

    private Set<String> findReachableCities(City fromCity, int range, Set<String> citiesNames,
                                            HashMap<String, Integer> shortestWay) {
        Set<CitiesDirection> reachableCities = citiesDirectionRepository
                .findAllByFromAndDistanceIsLessThanEqual(fromCity, range);
        reachableCities.forEach((CitiesDirection direction) -> {
            String cityNameTo = direction.getTo().getCityName();
            if (!shortestWay.containsKey(cityNameTo))
                shortestWay.put(cityNameTo, range - direction.getDistance());
            if (shortestWay.get(cityNameTo) <= range - direction.getDistance()) {
                if (direction.getDistance() <= range) {
                    shortestWay.put(cityNameTo, range - direction.getDistance());
                    citiesNames.add(cityNameTo);
                    findReachableCities(direction.getTo(), range - direction.getDistance(), citiesNames, shortestWay);
                }
            }
        });
        return citiesNames;
    }
}

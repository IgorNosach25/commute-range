package com.itechart.commuterange.services;

import com.itechart.commuterange.model.City;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CityRangeCounter {

    Set<String> getReachableCities(City cityFrom, int range);

}

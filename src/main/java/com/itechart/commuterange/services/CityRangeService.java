package com.itechart.commuterange.services;

import com.itechart.commuterange.model.City;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CityRangeService {
    Set<City> getAvailableCitiesByRange(City from, int city);
}

package com.itechart.commuterange.services;

import com.itechart.commuterange.exceptions.CityNotFoundException;
import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import com.itechart.commuterange.repositories.CitiesDirectionRepository;
import com.itechart.commuterange.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CityDirectionServiceImp implements CityDirectionService {

    private CityRepository cityRepository;
    private CityRangeCounter cityRangeService;
    private CitiesDirectionRepository citiesDirectionRepository;

    @Autowired
    public CityDirectionServiceImp(CityRepository cityRepository,
                                   CityRangeCounter cityRangeService,
                                   CitiesDirectionRepository citiesDirectionRepository) {
        this.cityRepository = cityRepository;
        this.cityRangeService = cityRangeService;
        this.citiesDirectionRepository = citiesDirectionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> getReachableCitiesNames(String fromCity, int range) {
        if (range < 0) throw new IllegalArgumentException("The range is not correct");
        else if (fromCity == null || fromCity.isEmpty()) throw new IllegalArgumentException("The city is not correct");
        City city = cityRepository.findByCityName(fromCity);
        if (city == null) {
            String err = String.format("The city [%s] is not found", fromCity);
            throw new CityNotFoundException(err);
        } else return cityRangeService.getReachableCities(city, range);
    }

    @Override
    @Transactional
    public void saveDirectionBetweenCities(String from, String to, int range) {
        boolean areParametersValid = from != null && to != null
                && !from.isEmpty() && !to.isEmpty() && range >= 0;
        if (!areParametersValid) throw new IllegalArgumentException();
        City fromCity = saveCityOrGetIfExist(from);
        City toCity = saveCityOrGetIfExist(to);
        if (citiesDirectionRepository.existsByFromAndTo(fromCity, toCity)) {
            CitiesDirection byFromAndTo = citiesDirectionRepository.findByFromAndTo(fromCity, toCity);
            byFromAndTo.setDistance(range);
            CitiesDirection byToAndFrom = citiesDirectionRepository.findByFromAndTo(toCity, fromCity);
            byToAndFrom.setDistance(range);
        } else {
            createCitiesDirection(fromCity, toCity, range);
        }
    }

    private City saveCityOrGetIfExist(String name) {
        City city;
        if (cityRepository.existsByCityName(name))
            city = cityRepository.findByCityName(name);
        else {
            city = new City(name);
            cityRepository.save(city);
        }
        return city;
    }

    private void createCitiesDirection(City from, City to, int range) {
        CitiesDirection citiesDirection = new CitiesDirection();
        CitiesDirection citiesDirection1 = new CitiesDirection();
        citiesDirection1.setFrom(from);
        citiesDirection1.setTo(to);
        citiesDirection.setFrom(to);
        citiesDirection.setDistance(range);
        citiesDirection.setTo(from);
        citiesDirection1.setDistance(range);
        citiesDirectionRepository.save(citiesDirection);
        citiesDirectionRepository.save(citiesDirection1);
    }
}

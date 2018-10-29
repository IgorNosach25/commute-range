package com.itechart.commuterange.services;

import com.itechart.commuterange.exceptions.CityNotFoundException;
import com.itechart.commuterange.model.City;
import com.itechart.commuterange.repositories.CitiesDirectionRepository;
import com.itechart.commuterange.repositories.CityRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class CityDirectionServiceImpTest {
    private CityDirectionService cityDirectionService;
    @Mock
    private CityRangeCounter cityRangeCounter;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private CitiesDirectionRepository citiesDirectionRepository;

    private Set<String> foundedCities;

    @Before
    public void setUp() {
        cityDirectionService = new CityDirectionServiceImp(cityRepository, cityRangeCounter, citiesDirectionRepository);
        foundedCities = new HashSet<>();
        foundedCities.add("Kiev");
        foundedCities.add("Madrid");
        foundedCities.add("London");
    }

    @Test(expected = IllegalArgumentException.class)
    public void getReachableCitiesShouldThrowIllegalArgumentExceptionIfRangeIsNotCorrect() {
        String cityName = "Minsk";
        cityDirectionService.getReachableCitiesNames(cityName, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getReachableCitiesShouldThrowIllegalArgumentExceptionIfCityArgumentIsNotCorrect() {
        cityDirectionService.getReachableCitiesNames(null, -1);
    }

    @Test(expected = CityNotFoundException.class)
    public void getReachableCitiesShouldThrowCityNotFoundExceptionIfCityIsNotFound() {
        String cityName = "Minsk";
        given(cityRepository.findByCityName(cityName)).willReturn(null);
        cityDirectionService.getReachableCitiesNames(cityName, 200);
    }

    @Test
    public void getReachableCitiesShouldReturnAllCitiesIfRangeMoreOrEqualsToDistanceSum() {
        String cityName = "Minsk";
        given(citiesDirectionRepository.distancesSum()).willReturn(5000);
        given(cityRepository.findAllCitiesNames()).willReturn(foundedCities);
        Set<String> reachableCitiesNames = cityDirectionService.getReachableCitiesNames(cityName, Integer.MAX_VALUE);
        Assert.assertEquals(foundedCities, reachableCitiesNames);
    }

    @Test
    public void getReachableCitiesShouldReturnReachableCitiesFromCityWithRange() {
        String cityName = "Minsk";
        City city = new City(cityName);
        given(cityRepository.findByCityName(cityName)).willReturn(city);
        given(cityRangeCounter.getReachableCities(city, 200)).willReturn(foundedCities);
        Set<String> reachableCitiesNames = cityDirectionService.getReachableCitiesNames(cityName, 200);
        Assert.assertEquals(foundedCities, reachableCitiesNames);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveDirectionBetweenCitiesShouldThrowIllegalArgumentExceptionIfParametersAreNull() {
        cityDirectionService.saveDirectionBetweenCities(null, null, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveDirectionBetweenCitiesShouldThrowIllegalArgumentExceptionIfRangeLessThen0() {
        cityDirectionService.saveDirectionBetweenCities("Kiev", "London", -1);
    }
}

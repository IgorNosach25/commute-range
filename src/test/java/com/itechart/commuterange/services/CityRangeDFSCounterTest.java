package com.itechart.commuterange.services;

import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import com.itechart.commuterange.repositories.CitiesDirectionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CityRangeDFSCounterTest {
    private CityRangeCounter cityRangeCounter;
    private Set<City> alreadyMarked = new HashSet<>();
    @Mock
    private CitiesDirectionRepository repository;
    private Set<CitiesDirection> fromKiev;
    private Set<CitiesDirection> fromMinsk;
    private Set<CitiesDirection> fromMoscow;
    private Set<CitiesDirection> fromMadrid;
    private Set<CitiesDirection> fromLondon;
    private Set<CitiesDirection> fromBrest;
    private Set<CitiesDirection> fromVitebsk;
    private Set<CitiesDirection> fromDoha;
    private Set<CitiesDirection> fromBarcelona;
    private Map<String, Set<CitiesDirection>> directions;

    @Before
    public void setUp() {
        cityRangeCounter = new CityRangeDFSCounter(repository);
        fromKiev = new HashSet<>();
        fromKiev.add(new CitiesDirection(new City("Kiev"), new City("Minsk"), 100));
        fromKiev.add(new CitiesDirection(new City("Kiev"), new City("Moscow"), 200));
        fromKiev.add(new CitiesDirection(new City("Kiev"), new City("London"), 250));

        fromMinsk = new HashSet<>();
        fromMinsk.add(new CitiesDirection(new City("Minsk"), new City("Kiev"), 100));
        fromMinsk.add(new CitiesDirection(new City("Minsk"), new City("Brest"), 250));
        fromMinsk.add(new CitiesDirection(new City("Minsk"), new City("Vitebsk"), 300));
        fromMinsk.add(new CitiesDirection(new City("Minsk"), new City("Madrid"), 150));


        fromMoscow = new HashSet<>();
        fromMoscow.add(new CitiesDirection(new City("Moscow"), new City("Kiev"), 200));
        fromMoscow.add(new CitiesDirection(new City("Moscow"), new City("Vitebsk"), 700));
        fromMoscow.add(new CitiesDirection(new City("Moscow"), new City("London"), 500));

        fromMadrid = new HashSet<>();
        fromMadrid.add(new CitiesDirection(new City("Madrid"), new City("Minsk"), 150));
        fromMadrid.add(new CitiesDirection(new City("Madrid"), new City("London"), 400));
        fromMadrid.add(new CitiesDirection(new City("Madrid"), new City("Doha"), 1200));
        fromMadrid.add(new CitiesDirection(new City("Madrid"), new City("Barcelona"), 100));


        fromLondon = new HashSet<>();
        fromLondon.add(new CitiesDirection(new City("London"), new City("Kiev"), 250));
        fromLondon.add(new CitiesDirection(new City("London"), new City("Moscow"), 500));
        fromLondon.add(new CitiesDirection(new City("London"), new City("Madrid"), 400));
        fromLondon.add(new CitiesDirection(new City("London"), new City("Doha"), 800));

        fromBrest = new HashSet<>();
        fromBrest.add(new CitiesDirection(new City("Brest"), new City("Minsk"), 250));
        fromBrest.add(new CitiesDirection(new City("Brest"), new City("Vitebsk"), 300));

        fromVitebsk = new HashSet<>();
        fromVitebsk.add(new CitiesDirection(new City("Vitebsk"), new City("Brest"), 300));
        fromVitebsk.add(new CitiesDirection(new City("Vitebsk"), new City("Minsk"), 300));
        fromVitebsk.add(new CitiesDirection(new City("Vitebsk"), new City("Moscow"), 150));

        fromDoha = new HashSet<>();
        fromDoha.add(new CitiesDirection(new City("Doha"), new City("London"), 800));
        fromDoha.add(new CitiesDirection(new City("Doha"), new City("Madrid"), 1200));
        fromDoha.add(new CitiesDirection(new City("Doha"), new City("Barcelona"), 1300));

        fromBarcelona = new HashSet<>();
        fromBarcelona.add(new CitiesDirection(new City("Barcelona"), new City("Madrid"), 100));
        fromBarcelona.add(new CitiesDirection(new City("Barcelona"), new City("Doha"), 1300));

        directions = new HashMap<>();
        directions.put("Kiev", fromKiev);
        directions.put("Minsk", fromMinsk);
        directions.put("Moscow", fromMoscow);
        directions.put("London", fromLondon);
        directions.put("Brest", fromBrest);
        directions.put("Vitebsk", fromVitebsk);
        directions.put("Madrid", fromMadrid);
        directions.put("Doha", fromDoha);
        directions.put("Barcelona", fromBarcelona);
    }

    @Test
    public void findReachableCitiesShouldReturnEmptyListIfCityDoesNotHaveConnectionsWithOtherCities() {
        City ny = new City("New-York");
        given(repository.findAllByFromAndDistanceIsLessThanEqual(ny, 400)).willReturn(Collections.EMPTY_SET);
        Set<String> reachableCities = cityRangeCounter.getReachableCities(ny, 400);
        Assert.assertEquals(reachableCities, Collections.emptySet());
    }

    @Test(expected = AssertionError.class)
    public void findReachableCitiesShouldThrowIllegalArgumentExceptionIfRangeLessThen0() {
        City kiev = new City("Kiev");
        cityRangeCounter.getReachableCities(kiev, -1);
    }


    @Test
    public void findReachableCitiesShouldReturnCorrectCities() {
        int range = 600;
        City kiev = new City("Kiev");
        Set<String> expectedCities = new HashSet<>();
        expectedCities.add("Barcelona");
        expectedCities.add("Minsk");
        expectedCities.add("Madrid");
        expectedCities.add("London");
        expectedCities.add("Kiev");
        expectedCities.add("Vitebsk");
        expectedCities.add("Brest");
        expectedCities.add("Moscow");
        mockDirectionsInDb(kiev, range);
        Set<String> reachableCities = cityRangeCounter.getReachableCities(kiev, range);
        Assert.assertEquals(reachableCities, expectedCities);
    }

    @Test
    public void findReachableCitiesShouldReturnAllCitiesIfRangeIsMax() {
        City city = new City("Doha");
        mockDirectionsInDb(city, Integer.MAX_VALUE);
        Set<String> reachableCities = cityRangeCounter.getReachableCities(city, Integer.MAX_VALUE);
        Set<String> expectedCities = new HashSet<>();
        expectedCities.add("Brest");
        expectedCities.add("Madrid");
        expectedCities.add("London");
        expectedCities.add("Kiev");
        expectedCities.add("Moscow");
        expectedCities.add("Minsk");
        expectedCities.add("Barcelona");
        expectedCities.add("Doha");
        expectedCities.add("Vitebsk");
        Assert.assertEquals(expectedCities, reachableCities);
    }

    @Test
    public void findReachableCitiesShouldReturnEmptyListIfThereAreNotReachableCitiesWithRange() {
        City city = new City("Moscow");
        mockDirectionsInDb(city, 199);
        Set<String> reachableCities = cityRangeCounter.getReachableCities(city, 199);
        Assert.assertEquals(Collections.emptySet(), reachableCities);
    }

    private void mockDirectionsInDb(City from, int range) {
        Set<CitiesDirection> citiesTo = directions.get(from.getCityName());
        if (citiesTo == null) return;
        for (CitiesDirection citiesDirection : citiesTo) {
            boolean areDirectionsValid = !alreadyMarked.contains(citiesDirection.getTo())
                    && citiesDirection.getDistance() <= range;
            if (areDirectionsValid) {
                Set<CitiesDirection> collect = citiesTo.stream()
                        .filter(streamDirection -> streamDirection.getDistance() <=
                                range).collect(Collectors.toSet());
                when(repository.findAllByFromAndDistanceIsLessThanEqual(from, range))
                        .thenReturn(collect);
                alreadyMarked.add(citiesDirection.getTo());
                mockDirectionsInDb(citiesDirection.getTo(), range - citiesDirection.getDistance());
            }
        }

    }

}

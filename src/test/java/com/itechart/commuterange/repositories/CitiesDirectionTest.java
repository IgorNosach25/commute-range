package com.itechart.commuterange.repositories;

import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CitiesDirectionTest {

    @Autowired
    private CitiesDirectionRepository citiesDirectionRepository;

    @Test
    public void existsByFromAndToShouldReturnTrueIfObjectsAreExist() {
        City kiev = new City("Kiev");
        City london = new City("London");
        CitiesDirection citiesDirection = new CitiesDirection(kiev, london, 300);
        citiesDirectionRepository.save(citiesDirection);
        boolean areExist = citiesDirectionRepository.existsByFromAndTo(kiev, london);
        Assert.assertTrue(areExist);
    }

    @Test
    public void findsByFromAndToShouldReturnCitiesDirectionIfObjectsAreExist() {
        City kiev = new City("Kiev");
        City london = new City("London");
        CitiesDirection expectedCitiesDirection = new CitiesDirection(kiev, london, 300);
        citiesDirectionRepository.save(expectedCitiesDirection);
        CitiesDirection foundedCitiesDirection = citiesDirectionRepository.findByFromAndTo(kiev, london);
        Assert.assertEquals(expectedCitiesDirection, foundedCitiesDirection);
    }

    @Test
    public void findAllByFromAndDistanceIsLessThanEqualShouldReturnCorrectDistances() {
        City kiev = new City("Kiev");
        City london = new City("London");
        CitiesDirection expectedCitiesDirection = new CitiesDirection(kiev, london, 300);


        City moscow = new City("Moscow");
        CitiesDirection expectedCitiesDirection2 = new CitiesDirection(kiev, moscow, 200);


        City madrid = new City("Madrid");
        CitiesDirection expectedCitiesDirection3 = new CitiesDirection(kiev, madrid, 100);

        citiesDirectionRepository.save(expectedCitiesDirection);
        citiesDirectionRepository.save(expectedCitiesDirection2);
        citiesDirectionRepository.save(expectedCitiesDirection3);

        Set<CitiesDirection> directions =
                citiesDirectionRepository.findAllByFromAndDistanceIsLessThanEqual(kiev, 200);
        Assert.assertEquals(directions.size(), 2);

        Set<CitiesDirection> directions2 =
                citiesDirectionRepository.findAllByFromAndDistanceIsLessThanEqual(kiev, 300);
        Assert.assertEquals(directions2.size(), 3);

        Set<CitiesDirection> directions3 =
                citiesDirectionRepository.findAllByFromAndDistanceIsLessThanEqual(kiev, 99);
        Assert.assertEquals(directions3.size(), 0);
    }
}

package com.itechart.commuterange.repositories;

import com.itechart.commuterange.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryTest {


    @Autowired
    private CityRepository cityRepository;

    @Test
    public void saveShouldSaveCityToDB() {
        City city = new City("Kiev");
        cityRepository.save(city);
        boolean isExist = cityRepository.existsByCityName("Kiev");
        Assert.assertTrue(isExist);
    }


    @Test
    public void removeShouldRemoveCityToDb() {
        City city = new City("Kiev");
        cityRepository.save(city);
        cityRepository.delete(city);
        boolean isExist = cityRepository.existsByCityName("Kiev");
        Assert.assertFalse(isExist);
    }

    @Test
    public void findByCityNameShouldReturnCityName() {
        City city = new City("Kiev");
        cityRepository.save(city);
        City kiev = cityRepository.findByCityName("Kiev");
        Assert.assertEquals(city, kiev);
    }

    @Test
    public void findAllCitiesNameShouldReturnAllCitiesInDb() {
        City kiev = new City("Kiev");
        cityRepository.save(kiev);
        City london = new City("London");
        cityRepository.save(london);
        City moscow = new City("Moscow");
        cityRepository.save(moscow);
        City doha = new City("Doha");
        cityRepository.save(doha);
        City minsk = new City("Minsk");
        cityRepository.save(minsk);

        Set<String> expectedCities = new HashSet<>();
        expectedCities.add(kiev.getCityName());
        expectedCities.add(london.getCityName());
        expectedCities.add(moscow.getCityName());
        expectedCities.add(doha.getCityName());
        expectedCities.add(minsk.getCityName());

        Set<String> allCities = cityRepository.findAllCitiesNames();
        Assert.assertEquals(expectedCities, allCities);
    }

}

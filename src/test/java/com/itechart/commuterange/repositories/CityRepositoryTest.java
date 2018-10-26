package com.itechart.commuterange.repositories;

import com.itechart.commuterange.model.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}

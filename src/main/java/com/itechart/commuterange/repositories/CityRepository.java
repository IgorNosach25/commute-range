package com.itechart.commuterange.repositories;


import com.itechart.commuterange.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CityRepository extends CrudRepository<City, Integer> {

    City findByCityName(String name);

    boolean existsByCityName(String cityName);

    @Query("SELECT c.cityName FROM City c")
    Set<String> findAllCitiesNames();
}

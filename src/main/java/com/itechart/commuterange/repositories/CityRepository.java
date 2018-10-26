package com.itechart.commuterange.repositories;


import com.itechart.commuterange.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {

    City findByCityName(String name);

    boolean existsByCityName(String cityName);

}

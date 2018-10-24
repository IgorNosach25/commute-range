package com.itechart.commuterange.repositories;


import com.itechart.commuterange.model.City;
import org.springframework.data.repository.CrudRepository;

interface CityRepository extends CrudRepository<City, Integer> {
    City findByName(String name);
}

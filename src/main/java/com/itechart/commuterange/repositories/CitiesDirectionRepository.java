package com.itechart.commuterange.repositories;

import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CitiesDirectionRepository extends CrudRepository<CitiesDirection, Integer> {

    Set<CitiesDirection> findAllByFromAndDistanceIsLessThanEqual(City city, int distance);

    boolean existsByFromAndTo(City from, City to);

    CitiesDirection findByFromAndTo(City from, City to);
}

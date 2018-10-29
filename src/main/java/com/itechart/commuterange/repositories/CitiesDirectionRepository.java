package com.itechart.commuterange.repositories;

import com.itechart.commuterange.model.CitiesDirection;
import com.itechart.commuterange.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CitiesDirectionRepository extends CrudRepository<CitiesDirection, Integer> {

    boolean existsByFromAndTo(City from, City to);

    CitiesDirection findByFromAndTo(City from, City to);

    Set<CitiesDirection> findAllByFromAndDistanceIsLessThanEqual(City city, int distance);

    @Query("SELECT SUM(cd.distance) from CitiesDirection cd")
    int distancesSum();
}

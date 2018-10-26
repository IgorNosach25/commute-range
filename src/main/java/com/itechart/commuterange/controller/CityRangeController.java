package com.itechart.commuterange.controller;

import com.itechart.commuterange.services.CityDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("city-range/")
public class CityRangeController {

    private final CityDirectionService cityDirectionService;

    @Autowired
    public CityRangeController(CityDirectionService cityDirectionService) {
        this.cityDirectionService = cityDirectionService;
    }

    @GetMapping(value = "reachable-cities")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> getReachableCities(@RequestParam(value = "cityName") String cityName,
                                 @RequestParam(value = "range") Integer range) {
        return cityDirectionService.getReachableCitiesNames(cityName, range);
    }
}

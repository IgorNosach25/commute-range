package com.itechart.commuterange.config;

import com.itechart.commuterange.services.CityDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {


    private final CityDirectionService cityDirectionService;

    @Autowired
    public ApplicationReadyEventListener(CityDirectionService cityDirectionService) {
        this.cityDirectionService = cityDirectionService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        cityDirectionService.saveDirectionBetweenCities("Kiev", "London", 250);
        cityDirectionService.saveDirectionBetweenCities("Kiev", "Moscow", 200);
        cityDirectionService.saveDirectionBetweenCities("Kiev", "Minsk", 100);
        cityDirectionService.saveDirectionBetweenCities("Minsk", "Brest",250);
        cityDirectionService.saveDirectionBetweenCities("Minsk", "Vitebsk",300);
        cityDirectionService.saveDirectionBetweenCities("Minsk", "Madrid",150);
        cityDirectionService.saveDirectionBetweenCities("Moscow", "Vitebsk", 700);
        cityDirectionService.saveDirectionBetweenCities("London", "Moscow",500);
        cityDirectionService.saveDirectionBetweenCities("London", "Madrid",400);
        cityDirectionService.saveDirectionBetweenCities("London", "Doha",800);
        cityDirectionService.saveDirectionBetweenCities("Brest", "Vitebsk",300);
        cityDirectionService.saveDirectionBetweenCities("Madrid", "Doha",1200);
        cityDirectionService.saveDirectionBetweenCities("Madrid", "Barcelona",100);
        cityDirectionService.saveDirectionBetweenCities("Barcelona", "Doha",1300);
    }
}

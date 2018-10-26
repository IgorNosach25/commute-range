package com.itechart.commuterange.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itechart.commuterange.exceptions.CityNotFoundException;
import com.itechart.commuterange.services.CityDirectionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRangeController.class)
public class CityRangeControllerTest {
    @Autowired
    private MockMvc mvc;

    private Gson gson = new GsonBuilder().serializeNulls().create();
    @MockBean
    private CityDirectionService userService;
    private Set<String> foundedCities;

    @Before
    public void setUp() {
        foundedCities = new HashSet<>();
        foundedCities.add("Kiev");
        foundedCities.add("Madrid");
        foundedCities.add("London");
    }

    @Test
    public void getReachableCitiesShouldReturnAvailableCitiesByRange() throws Exception {
        String cityName = "Minsk";
        given(userService.getReachableCitiesNames(cityName, 200)).willReturn(foundedCities);
        MvcResult result = mvc.perform(get("http://localhost:8080/city-range/reachable-cities")
                .param("cityName", cityName)
                .param("range", "200"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals(gson.toJson(foundedCities), result.getResponse().getContentAsString());
    }

    @Test()
    public void getReachableCitiesByRangeShouldThrowExceptionIfTheCityIsNotFound() throws Exception {
        String cityName = "Moscow";
        String errMess = String.format("The city [%s] is not found", cityName);
        given(userService.getReachableCitiesNames(cityName, 200))
                .willThrow(new CityNotFoundException(errMess));
        MvcResult result = mvc.perform(get("http://localhost:8080/city-range/reachable-cities")
                .param("cityName", cityName)
                .param("range", "200"))
                .andDo(print())
                .andExpect(status().isNotFound()).andReturn();
        Assert.assertEquals(gson.toJson(errMess), "\"" + result.getResponse().getContentAsString() + "\"");
    }
}

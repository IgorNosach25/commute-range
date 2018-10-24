package com.itechart.commuterange.controller;

import com.google.gson.Gson;
import com.itechart.commuterange.model.City;
import com.itechart.commuterange.services.CityRangeService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRangeController.class)
public class CityRangeControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private Gson gson;
    @MockBean
    private CityRangeService userService;
    private Set<City> c;


}

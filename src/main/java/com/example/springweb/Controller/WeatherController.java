package com.example.springweb.Controller;


import com.example.springweb.Model.Weather;
import com.example.springweb.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/")
    public String index(Model model) {
//    List<Weather> weathers = weatherService.convert();
        return "index";
    }


}

package com.example.springweb.Controller;


import com.example.springweb.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("weather", weatherService.getCurrentWeather("Sydney","Australia"));
        model.addAttribute("weathers", weatherService.getCurrentWeathers("Kathmandu","Nepal"));
        return "index";
    }


}

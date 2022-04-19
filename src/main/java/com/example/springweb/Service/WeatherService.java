package com.example.springweb.Service;

import com.example.springweb.Model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class WeatherService {

    //url
    private final static String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=Kathmandu,Nepal&appid=05950c29b857993bf578618537855ecd";
    //apikey
    private String apiKey;
    //restTemplate and objectMapper
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    //Constructor
    //URL ResponseENTITY


    public WeatherService(RestTemplateBuilder restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate.build();
        this.objectMapper = objectMapper;
    }


    //JsonNode
    //Yo method le api bata ako value linxa
    public Weather convert(ResponseEntity<String> responseEntity) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            return new Weather((jsonNode.path("weather").get(0).path("description")).asText(),
                    BigDecimal.valueOf(jsonNode.path("main").asDouble()),
                    BigDecimal.valueOf(jsonNode.path("main").path("feels_like").asDouble()));

        } catch (JsonProcessingException e) {

            // YO JSON parse vayena vani yo exception falxa
            throw new RuntimeException("Error Parsing JSON: ");
        }

    }
}
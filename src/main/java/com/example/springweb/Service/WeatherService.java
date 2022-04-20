package com.example.springweb.Service;

import com.example.springweb.Model.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class WeatherService {

    //url
    private final static String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city},{country}&appid={api}";
    //apikey
    //yesko value application.properties bata aauxa
    @Value("${api}")
    private String apiKey;
    //restTemplate and objectMapper
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;



    //Constructor
    public WeatherService(RestTemplateBuilder restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate.build();
        this.objectMapper = objectMapper;
    }


    public Weather getCurrentWeather(String city, String country) {
        URI uri = null;
        uri = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
        //Ako jati sabai response lai string ma lera janxa
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return convert(responseEntity);
    }
    //URL ResponseENTITY
    public Weather getCurrentWeathers(String city, String country) {
        URI uri = null;
        uri = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
        //Ako jati sabai response lai string ma lera janxa
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return convert(responseEntity);
    }

    //JsonNode
    //Yo method le api bata ako value linxa
    public Weather convert(ResponseEntity<String> responseEntity) {
        try {
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            return new Weather((jsonNode.path("weather").get(0).path("description")).asText(),
                    BigDecimal.valueOf(jsonNode.path("main").path("temp").asDouble()),
                    BigDecimal.valueOf(jsonNode.path("main").path("feels_like").asDouble()));

        } catch (JsonProcessingException e) {

            // YO JSON parse vayena vani yo exception falxa
            throw new RuntimeException("Error Parsing JSON: ");
        }

    }

}
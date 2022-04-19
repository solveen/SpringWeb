package com.example.springweb.Config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplate){
        return restTemplate.build();
    }
}

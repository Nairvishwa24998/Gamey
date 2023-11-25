package com.shoppingwebsiteinterface.demo.gameconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {

    String baseUrl = " https://api.rawg.io/api/games";

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

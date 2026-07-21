package net.codex.journalApp.service;

import net.codex.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey ;

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY",city).replace("API_KEY",apiKey);
         ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
          WeatherResponse body = response.getBody();
          return body;

        
    }
}

package net.codex.journalApp.service;

import net.codex.journalApp.api.response.WeatherResponse;
import net.codex.journalApp.cache.AppCache;
import net.codex.journalApp.constants.Placeholders;
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

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey ;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_KEY.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apiKey);
         ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
          WeatherResponse body = response.getBody();
          return body;

        
    }
}

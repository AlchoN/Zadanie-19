package com.example.weather_service.controller;

import com.example.weather_service.model.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @GetMapping("/{latitude}/{longitude}")
    public ResponseEntity<Weather> getWeatherByCoordinates(@PathVariable double latitude, @PathVariable double longitude) {
        Weather weather = new Weather();
        weather.setTemperature(25.5);
        weather.setCondition("Sunny");
        return new ResponseEntity<>(weather, HttpStatus.OK);
    }
}
package com.caparelli.controller

import com.caparelli.model.DailyForecast
import com.caparelli.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/weather")
class WeatherController(private val weatherService: WeatherService) {

    /**
     * API to retrieve the highest temperature for today in Celcius
     */
    @GetMapping("/daily-forecast")
    fun getTodayHighestTemperatureInCelsius(): Mono<DailyForecast> {
        return weatherService.convertWeatherForecastToDailyForecast(
            weatherService.getWeatherForecast()
        )
    }
}

package com.caparelli.controller

import com.caparelli.model.DailyForecast
import com.caparelli.service.WeatherService
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/weather")
class WeatherController(private val weatherService: WeatherService) {

    /**
     * API to retrieve the highest temperature for today in Celcius
     */
    @GetMapping("/daily-forecast/{WCO}/{gridX},{gridY}")
    fun getTodayHighestTemperatureInCelsius(@PathVariable(name = "WCO") wco: String,
                                            @PathVariable(name = "gridX") gridX: String,
                                            @PathVariable(name = "gridY") gridY: String): Mono<DailyForecast> {
        return weatherService.convertWeatherForecastToDailyForecast(
            runBlocking {
                weatherService.getWeatherForecast(wco, gridX, gridY)
            }
        )
    }
}

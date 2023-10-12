package com.caparelli.service

import com.caparelli.model.DailyForecast
import com.caparelli.model.Period
import com.caparelli.model.WeatherForecast
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class WeatherService {
    private val webClient = WebClient.builder()
        .baseUrl("https://api.weather.gov/gridpoints/MLB/33,70/forecast")
        .build()

    fun getWeatherForecast(): Mono<WeatherForecast> {
        return webClient
            .get()
            .retrieve()
            .bodyToMono(WeatherForecast::class.java)
    }

    fun convertWeatherForecastToDailyForecast(weatherForecast: Mono<WeatherForecast>): Mono<DailyForecast> {
        return weatherForecast.map {
            convertWeatherForecast(it)
        }
    }

    fun convertWeatherForecast(weatherForecast: WeatherForecast): DailyForecast {
        val highestTempInTheDay = findPeriodWithHighestTemperature(weatherForecast.properties!!.periods)
        if (highestTempInTheDay != null) {
            return DailyForecast(
                highestTempInTheDay.name,
                convertFahrenheitToCelsius(highestTempInTheDay.temperature),
                highestTempInTheDay.shortForecast
            )
        }
        return DailyForecast("invalid", 0.0, "")
    }

    fun findPeriodWithHighestTemperature(periods: List<Period>): Period? {
        return periods.maxByOrNull { it.temperature }
    }

    fun convertFahrenheitToCelsius(fahrenheit: Double?): Double {
        if (fahrenheit != null) {
            return  (fahrenheit - 32) * 5 / 9
        }
        return 0.0
    }
}

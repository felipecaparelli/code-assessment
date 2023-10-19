package com.caparelli.service

import com.caparelli.model.DailyForecast
import com.caparelli.model.Period
import com.caparelli.model.WeatherForecast
import kotlinx.coroutines.runBlocking
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class WeatherService {

    fun buildWebClient(wco: String, gridX: String, gridY: String): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.weather.gov/gridpoints/$wco/$gridX,$gridY/forecast")
            .build()
    }

    @Cacheable(cacheNames= ["weatherCache"], key="#wco")
    suspend fun getWeatherForecast(wco: String, gridX: String, gridY: String): Mono<WeatherForecast> {
        return runBlocking<Mono<WeatherForecast>> {
            val resp = buildWebClient(wco, gridX, gridY)
                .get()
                .retrieve()
                .bodyToMono(WeatherForecast::class.java)
                .cache(Duration.ofSeconds(10))
                .cacheInvalidateIf{ it.type == "mock"}
            resp
        }.onErrorResume {throwable ->
            println("Error occurred: ${throwable.message}")
            Mono.just(WeatherForecast("mock",null, null))
        }
    }

    fun convertWeatherForecastToDailyForecast(weatherForecast: Mono<WeatherForecast>): Mono<DailyForecast> {
        return weatherForecast.map {
            convertWeatherForecast(it)
        }
    }

    fun convertWeatherForecast(weatherForecast: WeatherForecast): DailyForecast {
        val properties = weatherForecast.properties
        if (properties != null) {
            val highestTempInTheDay = findPeriodWithHighestTemperature(properties.periods)
            if (highestTempInTheDay != null) {
                return DailyForecast(
                    highestTempInTheDay.name,
                    convertFahrenheitToCelsius(highestTempInTheDay.temperature),
                    highestTempInTheDay.shortForecast
                )
            }
        }
        return DailyForecast(weatherForecast.type!!, 0.0, "")
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

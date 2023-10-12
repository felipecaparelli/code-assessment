package com.caparelli.service

import com.caparelli.model.Period
import com.caparelli.model.Properties
import com.caparelli.model.WeatherForecast
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate

internal class WeatherServiceTest {

    private val underTest: WeatherService = WeatherService()

    @Test
    fun convertWeatherForecast() {
        val list = arrayListOf(
            createPeriod("Monday", 32.0, "Cloudy"),
            createPeriod("Monday", 44.0, "Sunny"),
        )
        val weather = WeatherForecast("test", properties = Properties(
            periods = list
        ))
        val result = underTest.convertWeatherForecast(weather)
        assertEquals("Monday", result.day_name)
    }

    @Test
    fun findPeriodWithHighestTemperature() {
        val highest = createPeriod("Friday", 45.0, "Sunny")
        val list = listOf(
            highest,
            createPeriod("Friday", 32.0, "Cloudy"),
            createPeriod("Friday", 44.0, "Sunny"),
        )
        assertEquals(highest, underTest.findPeriodWithHighestTemperature(list))
    }

    @Test
    fun convertFahrenheitToCelsius() {
        assertEquals(0.0, underTest.convertFahrenheitToCelsius(32.0))
        assertEquals(10.0, underTest.convertFahrenheitToCelsius(50.0))
    }

    private fun createPeriod(name: String, temp: Double, shortForecast: String): Period {
        return Period(
            0,
            name,
            LocalDate.now().toString(),
            LocalDate.now().toString(),
            true,
            temp,
            "F",
            shortForecast,
            "none"
        )
    }
}

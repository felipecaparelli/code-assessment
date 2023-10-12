package com.caparelli.model

data class DailyForecast(
    val day_name: String,
    val temp_high_celsius: Double,
    val forecast_blurp: String
)

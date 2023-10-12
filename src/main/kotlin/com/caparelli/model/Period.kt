package com.caparelli.model

data class Period(
    val number: Int,
    val name: String,
    val startTime: String,
    val endTime: String,
    val isDaytime: Boolean,
    val temperature: Double,
    val temperatureUnit: String,
    val shortForecast: String,
    val detailedForecast: String
)

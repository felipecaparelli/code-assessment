package com.caparelli.model

data class WeatherForecast(
    val type       : String?           = null,
    val geometry   : Geometry?         = Geometry(),
    val properties : Properties?       = Properties()
)

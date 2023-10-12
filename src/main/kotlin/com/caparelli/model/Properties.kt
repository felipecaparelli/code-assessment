package com.caparelli.model

data class Properties (
  val updated           : String?            = null,
  val units             : String?            = null,
  val forecastGenerator : String?            = null,
  val generatedAt       : String?            = null,
  val updateTime        : String?            = null,
  val validTimes        : String?            = null,
  val elevation         : Elevation?         = Elevation(),
  val periods           : ArrayList<Period> = arrayListOf()
)

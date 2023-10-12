package com.caparelli.model

data class Geometry (
  val type        : String?                                 = null,
  val coordinates : ArrayList<ArrayList<ArrayList<Double>>> = arrayListOf()
)

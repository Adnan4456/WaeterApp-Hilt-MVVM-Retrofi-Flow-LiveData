package com.example.weatherapp.Model

data class City (
    val weather: ArrayList<Weather>,
    val main:Main,
    val wind:Wind,
    val name:String
    )

data class Weather(
    val description: String
)

data class Main(
    val temp: Double,
    val humidity:Int
)
data class Wind(
    val speed:Float,
)
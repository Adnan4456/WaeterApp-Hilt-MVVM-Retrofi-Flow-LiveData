package com.example.weatherapp.Network

import com.example.weatherapp.Model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather/")
    suspend fun getCityData(
        @Query("q") q:String,
        @Query("appid") appId:String
    ): City
}
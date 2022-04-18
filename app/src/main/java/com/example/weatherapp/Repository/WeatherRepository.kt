package com.example.weatherapp.Repository

import com.example.weatherapp.Model.City
import com.example.weatherapp.Network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(city:String):Flow<City> = flow {
        val response= apiServiceImp.getCity(city,"f9c12eff7d0bad56486f81dcc99fd495")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}
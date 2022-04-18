package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        weatherViewModel.getCityData()
        initListener()

        weatherViewModel.weatherResponse.observe(this, Observer {response->

            Log.d("MainActivity"," values =  $response")
            if(response.weather[0].description == "clear sky" || response.weather[0].description == "mist"){
                Glide.with(this)
                    .load(R.drawable.clouds)
                    .into(binding.image)
            }else
                if(response.weather[0].description == "haze" || response.weather[0].description == "overcast clouds" || response.weather[0].description == "fog" ){
                    Glide.with(this)
                        .load(R.drawable.haze)
                        .into(binding.image)
                }else
                    if(response.weather[0].description == "rain"){
                        Glide.with(this)
                            .load(R.drawable.rain)
                            .into(binding.image)
                    }
            binding.description.text=response.weather[0].description
            binding.name.text=response.name
            binding.degree.text=response.wind.toString()
            binding.speed.text=response.wind.speed.toString()
            binding.temp.text=response.main.temp.toString()
            binding.humidity.text=response.main.humidity.toString()

        })

    }

    @ExperimentalCoroutinesApi
    private fun initListener()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.setSearchQuery(it) }
                Log.d("main", "onQueryTextChange: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }

}

//f9c12eff7d0bad56486f81dcc99fd495

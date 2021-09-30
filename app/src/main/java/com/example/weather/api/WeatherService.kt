package com.example.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object WeatherService {

    private const val BASE_URL="https://api.openweathermap.org/data/2.5/"
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: WeatherApi= getInstance().create(WeatherApi::class.java)

}
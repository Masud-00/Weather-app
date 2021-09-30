package com.example.weather.api

import android.text.Editable
import com.example.weather.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?")
    suspend fun getWeather(@Query("q") q: Editable, @Query("units") units: String, @Query("appid") appid: String): Response<Weather>
}
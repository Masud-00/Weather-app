package com.example.weather.repository

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.api.WeatherApi
import com.example.weather.model.Weather
import retrofit2.Response

class MainRepository(private val weatherApi: WeatherApi) {
   private val weatherList= MutableLiveData<Response<Weather>>()
    val weather: LiveData<Response<Weather>>
    get()=weatherList

    suspend fun getWeather(q: Editable, units: String, appid: String){

        try {
            val result=weatherApi.getWeather(q,units,appid)
            if (result.body() != null) {
                weatherList.postValue(Response.success(result.body()))
                Log.d("res", result.body()?.weather.toString())
                Log.d("res", "okk")
            }

        }
        catch (e:Exception){
            Log.d("error","error")
        }
    }

}
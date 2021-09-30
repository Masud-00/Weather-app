package com.example.weather.viewModel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.model.Weather
import com.example.weather.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: MainRepository): ViewModel() {

    fun getWeather(q: Editable, units: String, appid:String) {

       viewModelScope.launch {
           repository.getWeather(q,units,appid)
       }


    }
    val weather: LiveData<Response<Weather>>
        get()= repository.weather

}
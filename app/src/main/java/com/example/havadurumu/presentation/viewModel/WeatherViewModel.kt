package com.example.havadurumu.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.havadurumu.data.repository.WeatherRepository
import com.example.havadurumu.data.service.ApiClient
import com.example.havadurumu.data.service.ApiServices

class WeatherViewModel(val repository: WeatherRepository):ViewModel() {

    constructor():this(WeatherRepository(ApiClient().getClient().create(ApiServices::class.java)))
    fun loadCurrentWeather(lat:Double,lng:Double,unit:String)=
        repository.getCurrentWeather(lat,lng,unit)



    fun loadForecastWetaher(lat:Double,lng:Double,unit:String)=
        repository.getForecastWeather(lat,lng,unit)
}
package com.example.havadurumu.data.repository

import com.example.havadurumu.data.service.ApiServices

class WeatherRepository (val api: ApiServices){
    fun getCurrentWeather(lat:Double,lng:Double,unit:String)=api.getCurrentWeather(lat, lng,unit, ApiKey = "8fa77dfd74dbb4133764600c182738bc")


    fun getForecastWeather(lat:Double,lng:Double,unit:String)=
        api.getForecastWeather(lat, lng,unit, ApiKey = "8fa77dfd74dbb4133764600c182738bc")

}


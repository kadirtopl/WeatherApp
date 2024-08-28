package com.example.havadurumu.data.service

import com.example.havadurumu.data.model.CityResponseApi
import com.example.havadurumu.data.model.CurrentResponseApi
import com.example.project1712.model.ForecastResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") units:String,
        @Query("appid") ApiKey:String,
    ):retrofit2.Call<CurrentResponseApi>

    @GET("data/2.5/forecast")
    fun getForecastWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") units:String,
        @Query("appid") ApiKey:String,
    ):retrofit2.Call<ForecastResponseApi>
@GET("geo/1.0/direct")
fun  getCitiesList(
    @Query("q") q:String,
    @Query("limit") limit:Int,
    @Query("appid") ApiKey: String
):retrofit2.Call<CityResponseApi>

}
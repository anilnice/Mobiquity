package com.app.mobiquity.apiservice

import com.app.mobiquity.models.Forecast
import com.app.mobiquity.models.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun getWeather(@Query("lat") lat:Double,@Query("lon") lon:Double,@Query("appid") appid:String,@Query("units") units: String): Call<Weather>

    @GET("forecast")
    fun getForecast(@Query("lat") lat:Double,@Query("lon") lon:Double,@Query("appid") appid:String,@Query("units") units: String): Call<Forecast>

}
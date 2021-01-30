package com.app.mobiquity.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WeatherRepository(private val weatherDao: WeatherDao) {

    val allWords: Flow<List<Weatherdata>> = weatherDao.getAllWeatherdata()

    @WorkerThread
    suspend fun insert(weatherdata: Weatherdata){
        weatherDao.insertweather(weatherdata)
    }

    @WorkerThread
    suspend fun delete(weatherdata: Weatherdata){
        weatherDao.deleteweather(weatherdata)
    }

}
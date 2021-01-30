package com.app.mobiquity.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weatherdata")
    fun getAllWeatherdata(): Flow<List<Weatherdata>>

    @Insert
    suspend fun insertweather(word: Weatherdata)

    @Delete
    suspend fun deleteweather(weatherdata: Weatherdata)

}
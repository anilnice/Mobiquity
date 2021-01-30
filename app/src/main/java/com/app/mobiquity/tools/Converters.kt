package com.app.mobiquity.tools

import com.app.mobiquity.models.Weather
import com.google.gson.Gson


object Converters {
    fun jsonTostring(weather: Weather): String{
        return Gson().toJson(weather)
    }
    fun stringToJson(string: String): Weather{
        return Gson().fromJson(string, Weather::class.java)
    }
}
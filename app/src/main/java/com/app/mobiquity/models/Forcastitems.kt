package com.app.mobiquity.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Forcastitems(
    @SerializedName("clouds")
    val clouds: CloudsX,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: MainX,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("sys")
    val sys: SysX,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherXX>,
    @SerializedName("wind")
    val wind: WindX
)
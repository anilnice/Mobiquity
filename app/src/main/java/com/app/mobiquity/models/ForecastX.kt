package com.app.mobiquity.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ForecastX(
    @SerializedName("city")
    val city: CityX,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<Forcastitems>,
    @SerializedName("message")
    val message: Int
)
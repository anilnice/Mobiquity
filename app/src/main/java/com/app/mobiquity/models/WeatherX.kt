package com.app.mobiquity.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WeatherX(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)
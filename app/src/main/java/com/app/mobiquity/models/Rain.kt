package com.app.mobiquity.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Rain(
    @SerializedName("3h")
    val h: Double
)
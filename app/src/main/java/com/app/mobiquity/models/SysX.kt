package com.app.mobiquity.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SysX(
    @SerializedName("pod")
    val pod: String
)
package com.app.mobiquity.models


import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@Keep
data class Weather(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherX>,
    @SerializedName("wind")
    val wind: Wind,
    val iconurl:String="http://openweathermap.org/img/w/"+weather[0].icon+".png"
){
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) { // This methods should not have any return type, = declaration would make it return that object declaration.
            Glide.with(view.context).load(url).into(view)
        }
    }
}
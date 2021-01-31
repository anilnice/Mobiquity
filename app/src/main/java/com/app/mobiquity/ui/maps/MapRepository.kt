package com.app.mobiquity.ui.maps

import com.app.mobiquity.BuildConfig
import com.app.mobiquity.apiservice.ApiService
import com.google.android.gms.maps.model.LatLng

class MapRepository(val service: ApiService, val location:LatLng) {
    suspend fun getweather()= service.getWeather(location.latitude,location.longitude,BuildConfig.AppId,"metric")
}
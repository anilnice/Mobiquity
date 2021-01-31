package com.app.mobiquity.ui.details

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mobiquity.BuildConfig
import com.app.mobiquity.apiservice.ApiService
import com.app.mobiquity.models.Forecast
import com.app.mobiquity.models.Weather
import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {


    var weatherlive=MutableLiveData<Weather>()
    var forecastlive=MutableLiveData<Forecast>()
    var weatherloader=MutableLiveData<Boolean>(false)
    var forecastloader=MutableLiveData<Boolean>(false)

    fun getNetworkCall(apiCall: ApiService, location: LatLng?) {
        if (location != null) {
            apiCall.getWeather(location.latitude,location.longitude, BuildConfig.AppId,"metric").enqueue(
                    object :
                            Callback<Weather> {
                        override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                            weatherlive.value = response.body()!!
                            weatherloader.value = true
                        }

                        override fun onFailure(call: Call<Weather>, t: Throwable) {
                            Log.d(ContentValues.TAG, "onFailure: ${t.message}")
                            weatherloader.value = true
                        }
                    })

            apiCall.getForecast(location.latitude,location.latitude,BuildConfig.AppId,"metric").enqueue(
                    object :Callback<Forecast>{
                        override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                            forecastlive.value=response.body()!!
                            forecastloader.value=true
                        }

                        override fun onFailure(call: Call<Forecast>, t: Throwable) {
                            Log.d(ContentValues.TAG, "onFailure: ${t.message}")
                            forecastloader.value=true
                        }
                    }
            )
        }
    }

    public fun getWeather(): LiveData<Weather> {
        return weatherlive
    }

    public fun getForecast(): LiveData<Forecast>{
        return forecastlive
    }
}
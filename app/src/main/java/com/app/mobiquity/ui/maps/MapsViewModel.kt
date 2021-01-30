package com.app.mobiquity.ui.maps

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.app.mobiquity.BuildConfig
import com.app.mobiquity.apiservice.ApiService
import com.app.mobiquity.database.WeatherRepository
import com.app.mobiquity.database.Weatherdata
import com.app.mobiquity.models.Weather
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsViewModel(private val repository: WeatherRepository) : ViewModel() {
    val allWeatherdata: LiveData<List<Weatherdata>>  = repository.allWords.asLiveData()
    lateinit var maprepository:MapRepository
    var weatherlive=MutableLiveData<Weather>()

    fun insert(weatherdata: Weatherdata)=viewModelScope.launch{
        repository.insert(weatherdata)
    }


    fun getNetworkCall(apiCall: ApiService, location: LatLng?) {
        if (location != null) {
            apiCall.getWeather(location.latitude,location.longitude,BuildConfig.AppId).enqueue(
                object :
                    Callback<Weather> {
                    override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                        weatherlive.value = response.body()!!
                    }

                    override fun onFailure(call: Call<Weather>, t: Throwable) {
                        Log.d(TAG, "onFailure: ${t.message}")
                    }
                })
        }
    }

    public fun getWeather(): LiveData<Weather> {
        return weatherlive
    }

    /*fun getWeatherData() = liveData(viewModelScope.coroutineContext){
        try {
            coroutineScope {
                val task=async {
                    val result=maprepository.getweather()
                    emit(Result.success(result))
                }
                task.await()
            }
        }catch (e:Exception){
            coroutineScope {
                val task= async {
                    Log.d(ContentValues.TAG, "getPersonDetails: ${e.message}")
                }
                task.await()
            }
        }
    }*/

}
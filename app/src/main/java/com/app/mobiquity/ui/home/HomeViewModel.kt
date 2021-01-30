package com.app.mobiquity.ui.home

import androidx.lifecycle.*
import com.app.mobiquity.database.WeatherRepository
import com.app.mobiquity.database.Weatherdata
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class HomeViewModel(private val repository: WeatherRepository) : ViewModel() {

    val allWeatherdata: LiveData<List<Weatherdata>>  = repository.allWords.asLiveData()

    fun insert(weatherdata: Weatherdata)=viewModelScope.launch{
        repository.insert(weatherdata)
    }

    fun delete(weatherdata: Weatherdata)=viewModelScope.launch {
        repository.delete(weatherdata)
    }

}
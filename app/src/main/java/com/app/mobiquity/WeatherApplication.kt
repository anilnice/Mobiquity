package com.app.mobiquity

import android.app.Application
import com.app.mobiquity.database.WeatherRepository
import com.app.mobiquity.database.WeatherRoomdatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WeatherApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {WeatherRoomdatabase.getDatabase(this) }
    val repository by lazy { WeatherRepository(database!!.weatherDao()) }
}
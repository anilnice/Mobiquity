package com.app.mobiquity.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weatherdata::class],version = 2,exportSchema = false)
abstract class WeatherRoomdatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object{
        private var INSTANCE: WeatherRoomdatabase? = null

        fun getDatabase(context: Context) : WeatherRoomdatabase? {
            return if(INSTANCE==null){
                val instance = Room.databaseBuilder(context.applicationContext, WeatherRoomdatabase::class.java, "weather_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                instance
            }else{
                INSTANCE
            }
        }
    }
}
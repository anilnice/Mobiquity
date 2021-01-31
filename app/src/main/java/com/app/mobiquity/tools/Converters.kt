package com.app.mobiquity.tools

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.app.mobiquity.models.Weather
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


object Converters {
    fun jsonTostring(weather: Weather): String{
        return Gson().toJson(weather)
    }
    fun stringToJson(string: String): Weather{
        return Gson().fromJson(string, Weather::class.java)
    }

    fun getAdressFromlocation(context: Context, lat: Double, lng: Double):String{
        val geocoder: Geocoder
        val addresses: List<Address>
        geocoder = Geocoder(context, Locale.getDefault())

        addresses = geocoder.getFromLocation(lat, lng, 1)

        if(addresses.isEmpty()){
           return "UnKnown Address"
        }
        val address: String = addresses[0].getAddressLine(0)
        /*val city: String = addresses[0].getLocality()
        val state: String = addresses[0].getAdminArea()
        val country: String = addresses[0].getCountryName()
        val postalCode: String = addresses[0].getPostalCode()
        val knownName: String = addresses[0].getFeatureName()*/
        return address
    }

    fun  windDirection(degree: Double):String{
        if (degree>337.5) return "Northerly"
        if (degree>292.5) return "North Westerly"
        if(degree>247.5) return "Westerly"
        if(degree>202.5) return "South Westerly"
        if(degree>157.5) return "Southerly"
        if(degree>122.5) return "South Easterly"
        if(degree>67.5) return "Easterly"
        if(degree>22.5){return "North Easterly"}
        return "Northerly"
    }

    fun stringToLatlng(string: String):LatLng{
        val latLng = string.split(",").toTypedArray()
        val latitude = latLng[0].toDouble()
        val longitude = latLng[1].toDouble()
        val location = LatLng(latitude, longitude)
        return location
    }

    fun intToDate(milliseconds: Int):String{
        val transformedDate: String = SimpleDateFormat("H:mm  dd MMM yy").format(Date((milliseconds*1000).toLong()))
        return transformedDate
    }

}
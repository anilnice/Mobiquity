package com.app.mobiquity.ui.maps

import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.mobiquity.BuildConfig
import com.app.mobiquity.R
import com.app.mobiquity.apiservice.ApiService
import com.app.mobiquity.apiservice.RetrofitCall
import com.app.mobiquity.database.WeatherRepository
import com.app.mobiquity.database.WeatherRoomdatabase
import com.app.mobiquity.database.Weatherdata
import com.app.mobiquity.databinding.WeatherDialogBinding
import com.app.mobiquity.models.Weather
import com.app.mobiquity.tools.Converters

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsFragment : Fragment() {
    lateinit var mapViewModel: MapsViewModel
    lateinit var apiCall:ApiService
    val database by lazy { context?.let { WeatherRoomdatabase.getDatabase(it) } }
    val repository by lazy { WeatherRepository(database!!.weatherDao()) }
    lateinit var latLng: LatLng
    lateinit var loader: ProgressBar

    private val callback = OnMapReadyCallback { googleMap ->

        val india = LatLng(20.5937, 78.9629)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india,4f))
        googleMap.setOnMapLongClickListener {
            latLng=it
            mapViewModel.weatherloader.value=false
            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it,8f))
            googleMap.addMarker(MarkerOptions().position(it))
            mapViewModel.getNetworkCall(apiCall,it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        apiCall=RetrofitCall.service
        mapViewModel=ViewModelProvider(this, MapViewModelFactory(repository))[MapsViewModel::class.java]
        /*mapViewModel.allWeatherdata.observe(viewLifecycleOwner, Observer {


        })*/
        val v=inflater.inflate(R.layout.fragment_maps, container, false)
        loader=v.findViewById(R.id.weather_loader)
        mapViewModel.getWeather().observe(viewLifecycleOwner, Observer { weather ->
            activity?.let { it1 ->
                val binding = WeatherDialogBinding.inflate(layoutInflater)
                binding.weather = weather!!
                binding.executePendingBindings()
                val city= context?.let { Converters.getAdressFromlocation(it,weather.coord.lat,weather.coord.lon) }
                MaterialAlertDialogBuilder(it1).setTitle(city)
                    .setView(binding.root)
                    .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0!!.dismiss()
                        }
                    })
                    .setNegativeButton("Bookmark", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val location=""+latLng.latitude+","+latLng.longitude
                            mapViewModel.insert(Weatherdata(location,city!!,Converters.jsonTostring(weather)))
                            Log.d(TAG, "onClick: ${Converters.jsonTostring(weather)}")
                        }
                    }).show()
            }
        })

        mapViewModel.weatherloader.observe(viewLifecycleOwner, Observer {
            if(it!!){
                loader.visibility=View.GONE
            }else{
                loader.visibility=View.VISIBLE
            }
        })

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
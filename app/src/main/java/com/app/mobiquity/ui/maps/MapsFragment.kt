package com.app.mobiquity.ui.maps

import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val india = LatLng(20.5937, 78.9629)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india,4f))
        googleMap.setOnMapLongClickListener {
            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it,8f))
            googleMap.addMarker(MarkerOptions().position(it).title("Marker in Sydney"))
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

        mapViewModel.getWeather().observe(viewLifecycleOwner, Observer { weather ->
            Toast.makeText(context, "${weather.coord.lat},${weather.coord.lon}", Toast.LENGTH_LONG)
                .show()
            activity?.let { it1 ->
                val binding = WeatherDialogBinding.inflate(layoutInflater)
                binding.weather = weather!!
                binding.executePendingBindings()
                MaterialAlertDialogBuilder(it1)
                    .setView(binding.root)
                    .setTitle("Weather report")
                    .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            p0!!.dismiss()
                        }

                    })
                    .setNegativeButton("Book mark", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            mapViewModel.insert(Weatherdata(Converters.jsonTostring(weather)))
                            Log.d(TAG, "onClick: ${Converters.jsonTostring(weather)}")
                            //p0!!.dismiss()
                        }
                    }).show()

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
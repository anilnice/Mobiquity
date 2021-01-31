package com.app.mobiquity.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobiquity.R
import com.app.mobiquity.adapters.ForecastAdapter
import com.app.mobiquity.apiservice.ApiService
import com.app.mobiquity.apiservice.RetrofitCall
import com.app.mobiquity.database.Weatherdata
import com.app.mobiquity.databinding.FragmentDetailsBinding
import com.app.mobiquity.models.Forcastitems
import com.app.mobiquity.models.Forecast
import com.app.mobiquity.models.Weather
import com.app.mobiquity.tools.Converters
import com.app.mobiquity.ui.maps.MapViewModelFactory
import com.app.mobiquity.ui.maps.MapsViewModel
import com.google.android.gms.maps.model.LatLng

class DetailsFragment : Fragment() {

    private lateinit var dashboardViewModel: DetailsViewModel
    lateinit var detailsViewModel: DetailsViewModel
    lateinit var apiCall: ApiService
    lateinit var binding: FragmentDetailsBinding
    lateinit var forecastAdapter: ForecastAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel=ViewModelProvider(this)[DetailsViewModel::class.java]
        binding= FragmentDetailsBinding.inflate(layoutInflater)
        val weather=arguments?.getParcelable<Weatherdata>("data")

        apiCall= RetrofitCall.service
        forecastAdapter=ForecastAdapter(context, emptyList())

        binding.toolbarDetail.title="Weather Report"

        binding.forecastRecycler.layoutManager=LinearLayoutManager(context)
        binding.forecastRecycler.setHasFixedSize(true)
        binding.forecastRecycler.adapter= forecastAdapter

        val location=Converters.stringToLatlng(weather!!.location)
        detailsViewModel.getNetworkCall(apiCall,location)

        detailsViewModel.getWeather().observe(viewLifecycleOwner, Observer {
            binding.weather=it!!
            binding.weatherdata= weather
            binding.executePendingBindings()
        })

        detailsViewModel.forecastlive.observe(viewLifecycleOwner, Observer {
            val forecast=it!!.list
            forecastAdapter.setvalue(forecast)
        })

        detailsViewModel.forecastloader.observe(viewLifecycleOwner, Observer {
            binding.forecastloader=it!!
            binding.executePendingBindings()
        })
        detailsViewModel.weatherloader.observe(viewLifecycleOwner, Observer {
            binding.weatherloader=it!!
            binding.executePendingBindings()
        })

        return binding.root
    }
}
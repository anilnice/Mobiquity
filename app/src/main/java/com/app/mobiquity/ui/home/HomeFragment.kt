package com.app.mobiquity.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mobiquity.R
import com.app.mobiquity.adapters.WeatherAdapter
import com.app.mobiquity.database.WeatherRepository
import com.app.mobiquity.database.WeatherRoomdatabase
import com.app.mobiquity.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    lateinit var weatherAdapter: WeatherAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val database by lazy { context?.let { WeatherRoomdatabase.getDatabase(it) } }
        val repository by lazy { WeatherRepository(database!!.weatherDao()) }
        homeViewModel=ViewModelProvider(this, HomeViewmodelFactory(repository))[HomeViewModel::class.java]
        weatherAdapter=WeatherAdapter(context, emptyList(), homeViewModel)
        binding= FragmentHomeBinding.inflate(layoutInflater)

        binding.weatherRecycler.layoutManager=LinearLayoutManager(context)
        binding.weatherRecycler.hasFixedSize()
        binding.weatherRecycler.adapter= weatherAdapter

        homeViewModel.allWeatherdata.observe(viewLifecycleOwner, Observer {
            weatherAdapter.setvalue(it)
        })

        binding.addWeather.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_mapsFragment)
        }

        binding.toolbar.inflateMenu(R.menu.settings)
        binding.toolbar.title="Weather Info"
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.help -> {
                    findNavController().navigate(R.id.action_navigation_home_to_navigation_help)
                }
                R.id.settings -> {

                }
            }
            return@setOnMenuItemClickListener false
        }
        return binding.root
    }

}
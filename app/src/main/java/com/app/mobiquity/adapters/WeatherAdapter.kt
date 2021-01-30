package com.app.mobiquity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobiquity.database.Weatherdata
import com.app.mobiquity.databinding.WeatherItemBinding
import com.app.mobiquity.tools.Clickhandler
import com.app.mobiquity.ui.home.HomeViewModel

class WeatherAdapter(val context: Context?, var list: List<Weatherdata>,val homeViewModel: HomeViewModel) : RecyclerView.Adapter<WeatherAdapter.Viewholders>() {

    class Viewholders(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherdata: Weatherdata, homeViewModel: HomeViewModel) {
            binding.weatherdata=weatherdata
            val weather=com.app.mobiquity.tools.Converters.stringToJson(weatherdata.weather)
            binding.weather=weather
            binding.myclick= Clickhandler()
            binding.executePendingBindings()
            binding.deleteItem.setOnClickListener {
                homeViewModel.delete(weatherdata)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholders {
        val binding=WeatherItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholders(binding)
    }

    override fun onBindViewHolder(holder: Viewholders, position: Int) {
        holder.bind(list[position],homeViewModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setvalue(it: List<Weatherdata>) {
        list=it
        notifyDataSetChanged()
    }

}

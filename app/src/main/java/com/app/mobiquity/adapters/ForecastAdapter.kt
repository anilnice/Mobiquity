package com.app.mobiquity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.mobiquity.databinding.ForecastItemBinding
import com.app.mobiquity.models.Forcastitems
import com.app.mobiquity.models.Forecast

class ForecastAdapter(val context: Context?, var list: List<Forcastitems>) : RecyclerView.Adapter<ForecastAdapter.Viewholders>() {

    class Viewholders(val binding: ForecastItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastitem: Forcastitems) {
            binding.forecast=forecastitem
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholders {
        val binding=ForecastItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholders(binding)
    }

    override fun onBindViewHolder(holder: Viewholders, position: Int) {
        val forecast=list[position]
        holder.bind(forecast)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setvalue(forecast: List<Forcastitems>) {
        list=forecast
        notifyDataSetChanged()
    }

}

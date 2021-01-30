package com.app.mobiquity.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.mobiquity.R
import com.app.mobiquity.database.Weatherdata

class DetailsFragment : Fragment() {

    private lateinit var dashboardViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        val weather=arguments?.getParcelable<Weatherdata>("data")
        textView.text=weather!!.weather
        return root
    }
}
package com.app.mobiquity.tools

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.app.mobiquity.R
import com.app.mobiquity.database.Weatherdata

class Clickhandler {
    fun gotodetailScreen(view: View,weatherdata: Weatherdata){
        val bundle = bundleOf("data" to weatherdata)
        view.findNavController().navigate(R.id.action_navigation_home_to_navigation_details,bundle)
    }
}
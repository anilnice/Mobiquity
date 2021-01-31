package com.app.mobiquity.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.mobiquity.R
import com.app.mobiquity.ui.maps.MapViewModelFactory
import com.app.mobiquity.ui.maps.MapsViewModel


class HelpFragment : Fragment() {

    private lateinit var notificationsViewModel: HelpViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(HelpViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_help, container, false)
        val web=root.findViewById<WebView>(R.id.help_webview)
        web.loadUrl("file:///android_asset/help.html")
        return root
    }

}
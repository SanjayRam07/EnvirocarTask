package com.example.envirocartask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.envirocar.api.ApiInterface
import com.example.envirocar.api.MapRetrofit
import com.example.envirocartask.map.MapModule
import com.example.envirocartask.map.TrackTrace
import com.example.envirocartask.models.Feature
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mapRetrofit: MapRetrofit

    private lateinit var textView: TextView
    private lateinit var btn1: Button
    private lateinit var view: View
    private lateinit var mapView: MapModule
    private lateinit var trackTrace: TrackTrace
    private lateinit var RVAdapter: RecyclerView

    private lateinit var tracks: Unit
    private lateinit var features: List<Feature>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        view = inflater.inflate(R.layout.activity_main, null)
        setContentView(view)

        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID;

        textView = view.findViewById(R.id.editText)
        btn1 = view.findViewById(R.id.button)
        RVAdapter = view.findViewById(R.id.recyclerView)

        mapRetrofit = MapRetrofit()
        mapRetrofit.setup()

        mapView = MapModule(view)
        mapView.init()
    }

    override fun onStart() {
        super.onStart()

        btn1.setOnClickListener {
            val userId = textView.text.toString()
            tracks = mapRetrofit.getTracks(userId, RVAdapter)
        }
    }

}
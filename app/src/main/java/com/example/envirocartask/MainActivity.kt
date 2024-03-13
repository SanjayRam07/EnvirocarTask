package com.example.envirocartask

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.envirocar.api.ApiInterface
import com.example.envirocar.api.MapRetrofit
import com.example.envirocar.models.Tracks
import com.example.envirocartask.map.MapModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitBuilder: ApiInterface
    private lateinit var mapRetrofit: MapRetrofit

    private lateinit var textView: TextView
    private lateinit var btn: Button
    private lateinit var view: View
    private lateinit var mapView: MapModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(this)
        view = inflater.inflate(R.layout.activity_main, null)
        setContentView(view)

        Configuration.getInstance().userAgentValue = BuildConfig.APPLICATION_ID;

        textView = view.findViewById(R.id.textId)
        btn = view.findViewById(R.id.getBtn)
        
        mapRetrofit = MapRetrofit()
        mapRetrofit.setup()

        mapView = MapModule(view)
        mapView.init()

    }

    override fun onStart() {
        super.onStart()

        btn.setOnClickListener {
            var tracks = mapRetrofit.getTracks(textView)
        }
    }

}
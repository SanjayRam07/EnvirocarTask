package com.example.envirocar.api

import android.util.Log
import android.widget.TextView
import com.example.envirocar.models.Tracks
import com.example.envirocartask.map.TrackTrace
import com.example.envirocartask.models.Feature
import com.example.envirocartask.models.TrackPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.osmdroid.api.IGeoPoint
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Overlay
import org.osmdroid.views.overlay.Polyline
import retrofit2.Retrofit
class MapRetrofit {
    public lateinit var retrofitBuilder: ApiInterface

    fun setup() {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://envirocar.org/api/stable/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiInterface::class.java)
    }

    fun getTracks(textView:TextView) {
        var tracks = retrofitBuilder.getTracks()
        tracks.enqueue(object : Callback<Tracks?> {
            override fun onResponse(
                call: Call<Tracks?>,
                response: Response<Tracks?>
            ) {
                if (response.isSuccessful) {
                    val datas = response.body()!!
                    val tracks = datas.tracks
                    if (tracks.isNotEmpty()) {
                        textView.text = tracks[0].id
                    }
                }
            }

            override fun onFailure(call: Call<Tracks?>, t: Throwable) {
                textView.text = t.message.toString()
                Log.e("NWissue", t.message.toString(), t)
            }
        })
    }

    fun getTrackTrace(mapView: MapView) {
        Log.e("inside fun2", "good")

        var trackTrace = retrofitBuilder.getTrack()
        var polyline = Polyline()
        var geoPoints = ArrayList<GeoPoint>()

        trackTrace.enqueue(object : Callback<TrackPoints?> {
            override fun onResponse(
                call: Call<TrackPoints?>,
                response: Response<TrackPoints?>
            ) {

                Log.e("inside fun2", "super")
                if(response.isSuccessful) {
                    val datas = response.body()!!
                    val features = datas.features
                    if(features.isNotEmpty()) {
                        Log.e("inside fun2", "great")
                        for(feature in features) {
                            val point = feature.geometry.coordinates
                            val geoPoint = GeoPoint(point[0], point[1])
                            geoPoints.add(geoPoint)
                        }

                        polyline.setPoints(geoPoints)
                        mapView.overlay.add(polyline)
                    }
                }
            }

            override fun onFailure(call: Call<TrackPoints?>, t: Throwable) {
                Log.e("trace track", t.message.toString(), t)
            }
        })
    }
}
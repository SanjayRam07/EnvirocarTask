package com.example.envirocar.api

import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.envirocar.models.Tracks
import com.example.envirocartask.MainActivity
import com.example.envirocartask.R
import com.example.envirocartask.models.TrackPoints
import com.example.envirocartask.recyclerview.RecyclerAdapter
import com.example.envirocartask.shared.Shared
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
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

    fun getTracks(userId: String, RVAdapter: RecyclerView) {
        var tracks = retrofitBuilder.getTracks(userId)
        var trackList: ArrayList<String> = ArrayList<String>()

        tracks.enqueue(object : Callback<Tracks?> {
            override fun onResponse(
                call: Call<Tracks?>,
                response: Response<Tracks?>
            ) {
                if (response.isSuccessful) {
                    val datas = response.body()!!
                    val tracks = datas.tracks
                    if (tracks.isNotEmpty()) {
                        for(track in tracks) {
                            val id = track.id
                            trackList.add(id)
                        }
                        val adapter = RecyclerAdapter(trackList)
                        RVAdapter.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<Tracks?>, t: Throwable) {
                Log.e("NWissue", t.message.toString(), t)
            }
        })
    }

    fun getTrackTrace(trackId: String) {
        Log.e("inside fun2", "good")

        var trackTrace = retrofitBuilder.getTrack(trackId)
        var polyline = Polyline()
        var geoPoints = ArrayList<GeoPoint>()

        trackTrace.enqueue(object : Callback<TrackPoints?> {
            override fun onResponse(
                call: Call<TrackPoints?>,
                response: Response<TrackPoints?>
            ) {
                lateinit var geoPoint:GeoPoint

                Log.e("inside fun2", "super")
                if(response.isSuccessful) {
                    val datas = response.body()!!
                    val features = datas.features
                    Log.e("inside fun2", features.size.toString())
                    if(features.isNotEmpty()) {
                        Log.e("inside fun2", "great")
                        for(feature in features) {
                            val point = feature.geometry.coordinates
                            geoPoint = GeoPoint(point[1], point[0])
                            geoPoints.add(geoPoint)
                        }

                        polyline.setPoints(geoPoints)
                        polyline.width=15f
                        polyline.color=Color.RED

                        Shared.mapView.overlays.add(polyline)
                        Shared.mapView.zoomToBoundingBox(polyline.bounds, true)
                    }
                }
            }

            override fun onFailure(call: Call<TrackPoints?>, t: Throwable) {
                Log.e("trace track", t.message.toString(), t)
            }
        })
    }
}
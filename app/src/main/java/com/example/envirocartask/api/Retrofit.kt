package com.example.envirocar.api

import android.util.Log
import android.widget.TextView
import com.example.envirocar.models.Tracks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                    if (tracks.isNotEmpty() && tracks != null) {
                        textView.text = tracks[0].id
                    }
                }
            }

            override fun onFailure(call: Call<Tracks?>, t: Throwable) {
                textView.text = t.message.toString()
                Log.e("NWissue", t.message.toString())
            }
        })
    }
}
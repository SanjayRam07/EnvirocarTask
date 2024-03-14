package com.example.envirocar.api

import com.example.envirocar.models.Tracks
import com.example.envirocartask.models.TrackPoints
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET("users/sanjaygs07/tracks")
    fun getTracks(
        @Header("X-User") userHeader: String = "sanjaygs07",
        @Header("X-Token") tokenHeader: String = "sanjay123"
    ) : Call<Tracks>

    @GET("tracks/65e1d9a56549022592e528b6")
    fun getTrack(
        @Header("X-User") userHeader: String = "sanjaygs07",
        @Header("X-Token") tokenHeader: String = "sanjay123"
    ) : Call<TrackPoints>

}
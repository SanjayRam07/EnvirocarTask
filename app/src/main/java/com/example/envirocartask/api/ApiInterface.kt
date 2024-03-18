package com.example.envirocar.api

import com.example.envirocar.models.Tracks
import com.example.envirocartask.models.TrackPoints
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {

    @GET("users/{userId}/tracks")
    fun getTracks(
        @Path("userId") userId: String,
        @Header("X-User") userHeader: String = "sanjaygs07",
        @Header("X-Token") tokenHeader: String = "sanjay123"
    ) : Call<Tracks>

    @GET("tracks/{trackId}")
    fun getTrack(
        @Path("trackId") trackId: String,
        @Header("X-User") userHeader: String = "sanjaygs07",
        @Header("X-Token") tokenHeader: String = "sanjay123"
    ) : Call<TrackPoints>

}
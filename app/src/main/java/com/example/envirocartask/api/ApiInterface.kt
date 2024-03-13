package com.example.envirocar.api

import com.example.envirocar.models.Tracks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

    @GET("users/sanjaygs07/tracks")
    fun getTracks(
        @Header("X-User") userHeader: String = "sanjaygs07",
        @Header("X-Token") tokenHeader: String = "sanjay123"
    ) : Call<Tracks>

}
package com.example.envirocartask.shared

import com.example.envirocar.api.MapRetrofit
import org.osmdroid.views.MapView

class Shared {
    companion object {
        lateinit var mapRetrofit: MapRetrofit
        lateinit var mapView: MapView
    }
}
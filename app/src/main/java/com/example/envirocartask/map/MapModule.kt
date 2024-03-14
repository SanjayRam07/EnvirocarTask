package com.example.envirocartask.map

import android.graphics.Rect
import android.view.View
import com.example.envirocartask.R
import org.osmdroid.api.IMapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView

class MapModule(private val view: View) {
    lateinit var mMap: MapView
    lateinit var controller: IMapController

    fun init() {

        mMap = view.findViewById(R.id.mapView)
        controller = mMap.controller

        mMap.setTileSource(TileSourceFactory.MAPNIK)
        mMap.mapCenter
        mMap.setMultiTouchControls(true)
        mMap.getLocalVisibleRect(Rect())
        controller.setZoom(6.0)
    }

}
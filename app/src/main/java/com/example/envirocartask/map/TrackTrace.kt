package com.example.envirocartask.map

import com.example.envirocartask.models.Feature
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline

class TrackTrace {
    lateinit var polyline: Polyline
    lateinit var points: ArrayList<GeoPoint>
    lateinit var mapView: MapView

    companion object {
        lateinit var feature: List<Feature>
    }

    constructor(mapView: MapView) {
        this.mapView = mapView
        polyline = Polyline()
        points = ArrayList()
    }

    fun uploadPoints(features:List<Feature>) {
        for(feature in features) {
            val point: ArrayList<Double> = ArrayList(feature.geometry.coordinates)
            points.add(GeoPoint(point[0], point[1]))
        }
        polyline.setPoints(points)

        mapView.overlays.add(polyline)
        mapView.invalidate()
    }

    fun tracePoints() {
    }
}
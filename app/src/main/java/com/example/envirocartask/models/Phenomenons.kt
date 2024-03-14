package com.example.envirocartask.models

data class Phenomenons(
    val CO2Emission: CO2EmissionGPSBased,
    val Consumption: ConsumptionGPSBased,
    val GPSAccuracy: GPSAccuracy,
    val GPSAltitude: GPSAltitude,
    val GPSBearing: GPSBearing,
    val GPSSpeed: GPSSpeed,
    val MaximumGPSAcceleration: MaximumGPSAcceleration,
    val MinimumGPSAcceleration: MinimumGPSAcceleration
)
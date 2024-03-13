package com.example.envirocar.models

data class TracksItem(
    val begin: String,
    val end: String,
    val id: String,
    val length: Double,
    val modified: String,
    val name: String,
    val sensor: Sensor,
    val status: String
)
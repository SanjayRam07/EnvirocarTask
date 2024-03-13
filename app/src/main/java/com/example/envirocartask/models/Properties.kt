package com.example.envirocar.models

data class Properties(
    val constructionYear: Int,
    val engineDisplacement: Int,
    val fuelType: String,
    val id: String,
    val manufacturer: String,
    val model: String,
    val vehicleType: String,
    val weight: Int
)
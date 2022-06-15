package com.example.appcat.model

import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial")
    val imperial: String,
    @SerializedName("metric")
    val metric: String
)

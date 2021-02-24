package com.example.kotlinpracrice.model
import com.squareup.moshi.Json
//after this only property
data class MarsProperty(
    val id: String,
    @Json(name = "img_src")
    val imgSrcUrl: String,
    val type: String,
    val price: Double)
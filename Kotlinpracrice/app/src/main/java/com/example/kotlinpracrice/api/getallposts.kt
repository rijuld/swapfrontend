package com.example.kotlinpracrice.api
import com.example.kotlinpracrice.model.MarsProperty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface getallposts {
        @GET("realestate")
        fun getProperties():
                Call<List<MarsProperty>>

}
package com.example.kotlinpracrice.api

import android.util.Log
import com.example.kotlinpracrice.util.Constants.Companion.BASE_URL
import com.example.kotlinpracrice.util.Constants.Companion.BASE_URL2
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit2 = Retrofit.Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    val api:SimpleApi by lazy{
        Log.i("response","simpleapi")
        retrofit.create(SimpleApi ::class.java)
    }
}

object MarsApi {
    val retrofitService : getallposts by lazy {
        retrofit2.create(getallposts::class.java)
    }
}
//object as we want to keep this retrofit object as singleton
//api service
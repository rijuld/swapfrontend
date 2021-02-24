package com.example.kotlinpracrice.api

import   com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.model.User
import com.google.android.gms.auth.api.credentials.IdToken
import retrofit2.Response
import retrofit2.http.*

interface  SimpleApi {
    @GET("posts/1")
    suspend fun  getPost():Response<Post>

    @DELETE("posts/{postNumber}/")
    suspend fun  deletePost(
            @Path("postNumber") number:Int
    ):Response<Post>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    @POST("users")
    suspend fun userPost(
            @Body idToken: String,
            @Body phone_number:String,
    ): Response<String>
    
    @GET("posts/{postNumber}")//inside the path and the get annotation the value has to be the same
    suspend fun  getPost2(
        @Path("postNumber") number:Int
    ):Response<Post>

    @GET("require")
    suspend fun getCustomPost(
        @Query("user") id:Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>
}
package com.example.kotlinpracrice.api

import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.model.User
import com.example.kotlinpracrice.model.Usersend
import com.google.firebase.auth.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST

interface  SimpleApi {
    @GET("posts/1")
    suspend fun  getPost():Response<Post>

    @DELETE("require/{postNumber}/")
    suspend fun  deletePost(
            @Path("postNumber") number: Int
    ):Response<Post>

    @POST("require/")
    suspend fun pushPost(
            @Body post: Post
    ): Response<Post>

    @POST("authtok/")
    suspend fun getuser(
        @Body user: Usersend
    ): Response<Usersend>

    @GET("posts/{postNumber}")//inside the path and the get annotation the value has to be the same
    suspend fun  getPost2(
            @Path("postNumber") number: Int
    ):Response<Post>

    @GET("require")
    suspend fun getCustomPost(
            @Query("user") user: String,
            @Query("_sort") sort: String,
            @Query("_order") order: String
    ): Response<List<Post>>
}
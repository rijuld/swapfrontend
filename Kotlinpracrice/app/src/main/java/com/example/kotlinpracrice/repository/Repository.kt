package com.example.kotlinpracrice.repository

import android.util.Log
import com.example.kotlinpracrice.api.RetrofitInstance
import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.model.User
import retrofit2.Response

class Repository{
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
    suspend fun deletePost(number: Int): Response<Post> {
        return RetrofitInstance.api.deletePost(number)
    }
    suspend fun getPost2(number: Int):Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }
    suspend fun userPost(idToken: String,phone_number:String): Response<String> {
        return RetrofitInstance.api.userPost(idToken,phone_number)
    }
    suspend fun getCustomPost(id: Int,sort:String,order:String):Response<List<Post>>{
        Log.i("response","repository")
        return RetrofitInstance.api.getCustomPost(id,sort,order)
    }
    suspend fun pushPost(post: Post):Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

}
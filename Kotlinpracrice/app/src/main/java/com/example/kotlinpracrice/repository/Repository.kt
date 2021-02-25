package com.example.kotlinpracrice.repository

import android.util.Log
import android.widget.Toast
import com.example.kotlinpracrice.api.RetrofitInstance
import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.model.User
import com.example.kotlinpracrice.model.Usersend
import com.example.kotlinpracrice.model.idt
import retrofit2.Call
import retrofit2.Response

class Repository{
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
    suspend fun deletePost(number: Int): Response<Post> {
        Log.i("REpodel",number.toString())
        return RetrofitInstance.api.deletePost(number)
    }
    suspend fun getPost2(number: Int):Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPost(user:String,sort:String,order:String):Response<List<Post>>{
        Log.i("response","repository")
        return RetrofitInstance.api.getCustomPost(user,sort,order)
    }
    suspend fun pushPost(post: Post):Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }
    suspend fun getuser(user: Usersend): Response<Usersend> {
        Log.i("REpodel",user.toString())
        return RetrofitInstance.api.getuser(user)
    }

}
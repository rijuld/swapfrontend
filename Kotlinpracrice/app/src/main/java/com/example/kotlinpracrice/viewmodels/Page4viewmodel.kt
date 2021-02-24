package com.example.kotlinpracrice.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpracrice.api.MarsApi
import com.example.kotlinpracrice.model.MarsProperty
import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class page4viewmodel(private val repository: Repository) :ViewModel(){
    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myResponse2: MutableLiveData<List<MarsProperty>> = MutableLiveData()
    init{
        Log.i("page4viewmodel","page4 view model created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("page4viewmodel","page4 view model destroyed")
    }
    fun getMarsRealEstateProperties() {
        MarsApi.retrofitService.getProperties().enqueue( object: Callback<List<MarsProperty>> {
            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
                myResponse2.value = null
            }

            override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
                var list=response.body()
                myResponse2.value = list
            }
        })
    }
    fun getPost(){

        viewModelScope.launch {
            val response =repository.getPost()
            myResponse.value=response

        }
    }
    fun customPost(id:Int,sort: String,order:String){
        Log.i("response","custom post")
        viewModelScope.launch {
            val response =repository.getCustomPost(id,sort,order)
            myCustomPosts.value=response

        }
    }
    fun pushPost(post: Post){
        viewModelScope.launch {
            val response =repository.pushPost(post)
            myResponse.value=response

        }
    }
}
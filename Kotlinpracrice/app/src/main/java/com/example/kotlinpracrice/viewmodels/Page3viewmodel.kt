package com.example.kotlinpracrice.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.model.User
import com.example.kotlinpracrice.model.Usersend
import com.example.kotlinpracrice.model.idt
import com.example.kotlinpracrice.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class page3viewmodel(private val repository: Repository) :ViewModel(){
    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val deleteResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val postPosts: MutableLiveData<Response<Post>> = MutableLiveData()
    val use: MutableLiveData<Response<Usersend>> = MutableLiveData()
    var cloudt: MutableLiveData<String> = MutableLiveData()

    init{
        Log.i("page3viewmodel","page3 view model created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("page3viewmodel","page3 view model destroyed")
    }
    fun getPost(){

        viewModelScope.launch {
            val response =repository.getPost()
            myResponse.value=response

        }
    }

    fun getPost2(number:Int){

        viewModelScope.launch {
            val response =repository.getPost2(number)
            myResponse2.value=response

        }
    }
    fun customPost(user:String,sort: String,order:String){
        Log.i("response","custom post")
        viewModelScope.launch {
            val response =repository.getCustomPost(user,sort,order)

            myCustomPosts.value=response

        }
    }
    fun deletePost(number: Int){
        viewModelScope.launch {
            val response =repository.deletePost(number)
            deleteResponse.value=response

        }
    }
    fun pushPost(post: Post){
        viewModelScope.launch {
            val response =repository.pushPost(post)
            postPosts.value=response

        }
    }
    fun getUser(user:Usersend){

        viewModelScope.launch {
            val response =repository.getuser(user)
            use.value=response

        }
    }

}
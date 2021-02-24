package com.example.kotlinpracrice.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpracrice.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class Phonenumberviewmodel(private val repository: Repository) :ViewModel(){
    val userget: MutableLiveData<Response<String>> = MutableLiveData()
    init{
        Log.i("Phonenumberviewmodel","Phonenumberviewmodel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Phonenumberviewmodel","Phonenumberviewmodel destroyed")
    }
    fun userpost(idToken: String,phone_number:String){
        viewModelScope.launch {
            val response =repository.userPost(idToken,phone_number)
            userget.value=response

        }
    }

}
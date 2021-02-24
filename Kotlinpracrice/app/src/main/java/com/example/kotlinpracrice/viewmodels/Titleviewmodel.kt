package com.example.kotlinpracrice.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel

class titleviewmodel :ViewModel(){
    init{
        Log.i("titleviewmodel","title view model created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("titleviewmodel","title view model destroyed")
    }


}
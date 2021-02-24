package com.example.kotlinpracrice.viewmodels

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//the live data is always a val. Live data is an object and the data inside it might chagne but it will remain same
class page5viewmodel :ViewModel(){

    init{
        Log.i("page5viewmodel","page5 view model created!")
    }
}
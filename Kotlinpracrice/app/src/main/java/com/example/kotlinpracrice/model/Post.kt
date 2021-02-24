package com.example.kotlinpracrice.model

import com.example.kotlinpracrice.Phonenumber
import com.google.android.gms.auth.api.credentials.IdToken

//only four different fields in this

data class Post(
        val id:Int,
        val coursereq: String,
        val coursegiv: String,
        val user:String
)

data class User(
        val userid:String,
        val course:List<String>,
        val phonenumber: Phonenumber,
        val idToken: String
)
package com.example.kotlinpracrice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinpracrice.databinding.ActivityBlankBinding
import com.example.kotlinpracrice.messaging.values

class blank : AppCompatActivity() {
    private lateinit var binding: ActivityBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_blank)
        setContentView(R.layout.activity_blank)
        val senderEmail: String? = intent.getStringExtra(values.USER_EMAIL)
        val senderPhone: String? = intent.getStringExtra(values.USER_PHONE)
        val senderAssigned: String? = intent.getStringExtra(values.USER_ASSIGNED)
        binding.a.text = senderEmail
        binding.b.text = senderPhone
        binding.c.text = senderAssigned
    }
}
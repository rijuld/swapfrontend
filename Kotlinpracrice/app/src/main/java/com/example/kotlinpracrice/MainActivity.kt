package com.example.kotlinpracrice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlinpracrice.databinding.ActivityMainBinding
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.page3viewmodel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: page3viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        Log.i("MainActivity","onCreate called")
        val repository= Repository()
        val viewModelFactory= page3viewmodelfactory(repository)
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(page3viewmodel::class.java)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val navController=this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)/*
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Token", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("TAG", token.toString())
            Toast.makeText(baseContext, token.toString(), Toast.LENGTH_SHORT).show()
        })*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.myNavHostFragment)


        return navController.navigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.i("Main Activity","onStart called")
    }

}

package com.example.kotlinpracrice.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodels.page3viewmodel

class page3viewmodelfactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return page3viewmodel(repository) as T
    }
}
package com.example.cexupuniversity.service

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cexupuniversity.data.CourseRepository
import com.example.cexupuniversity.ui.screens.detail.DetailViewModel
import com.example.cexupuniversity.ui.screens.home.HomeViewModel

class ViewModelFactory(private val repository: CourseRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context))
            }.also {
                INSTANCE = it
            }
    }
}
package com.example.haircutapp.ui.styles

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haircutapp.hairstylesdatabase.HairstyleDao

//class StylesViewModelFactory (
//    private val dataSource: HairstyleDao,
//    private val application: Application) : ViewModelProvider.Factory {
//        @Suppress("unchecked_cast")
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(StylesViewModel::class.java)) {
//                return StylesViewModel(dataSource, application) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }
package com.example.haircutapp.ui.styles

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase

class StylesViewModel(val database: HairstyleDao, application: Application) : ViewModel() {

    val activities = database.getAllHairstyles()
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is styles Fragment"
//    }
//    val text: LiveData<String> = _text
}
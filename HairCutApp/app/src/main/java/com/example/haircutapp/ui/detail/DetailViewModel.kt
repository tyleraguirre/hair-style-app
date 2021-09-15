package com.example.haircutapp.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDao

class DetailViewModel(
    val database: HairstyleDao,
    application: Application
) : ViewModel() {

    private val _selectedMover = MutableLiveData<Hairstyle?>()
    val selectedMover: LiveData<Hairstyle?>
        get() = _selectedMover

    fun navigationComplete() {
        _selectedMover.value = null
    }


}
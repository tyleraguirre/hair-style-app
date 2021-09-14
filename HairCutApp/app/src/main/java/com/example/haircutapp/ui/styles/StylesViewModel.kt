package com.example.haircutapp.ui.styles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haircutapp.R
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase

class StylesViewModel(
    val database: HairstyleDao,
    application: Application) : ViewModel() {

    private val _selectedStyle = MutableLiveData<Hairstyle?>()
    val selectedStyle: LiveData<Hairstyle?>
        get() = _selectedStyle

    fun navigationComplete() {
        _selectedStyle.value = null
    }

    fun setHairstyle(hairstyle: Hairstyle) {
        _selectedStyle.value = hairstyle
    }
}

object Styles {

    val listOfStyles = mutableListOf<Hairstyle>(
        Hairstyle(0, "Fauxhawk", "Male", "Short", R.drawable.fauxhawk_sv_m),
        Hairstyle(1, "Buzzcut", "Male", "Short", R.drawable.buzzcut_sv_m)
//            Hairstyle(0, "Pompadore", "Male", "Short", R.drawable.fauxhawk_sv_m)

    )

}


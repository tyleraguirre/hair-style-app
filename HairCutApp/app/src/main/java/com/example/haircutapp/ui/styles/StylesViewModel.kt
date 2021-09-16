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

object StylesObject {

    val listOfStyles = mutableListOf<Hairstyle>(
        Hairstyle(0, "Fauxhawk", "Male", "Short",
            R.drawable.fauxhawk_sv_m, "https://en.wikipedia.org/wiki/Mohawk_hairstyle#Fauxhawk_variants",
            "https://www.pinterest.com/menshairtips/faux-hawk-hairstyle/"),
        Hairstyle(1, "Buzzcut", "Male", "Short",
            R.drawable.buzzcut_sv_m, "https://en.wikipedia.org/wiki/Buzz_cut",
        "https://www.pinterest.com/jambobimal/mens-hair-style-buzz-cut/"),
        Hairstyle(2, "Pompadour", "Male", "Short",
            R.drawable.pompadour_sv_m, "https://en.wikipedia.org/wiki/Pompadour_(hairstyle)",
            "https://www.pinterest.com/barbinccom/mens-pompadours/"),
        Hairstyle(3, "Quiff", "Male", "Short",
            R.drawable.quiff_sv_m, "https://en.wikipedia.org/wiki/Quiff",
            "https://www.pinterest.com/haircutinspirat/mens-quiff-hairstyles/"),
        Hairstyle(4, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(5, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(6, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(7, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(8, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(9, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(10, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/"),
        Hairstyle(11, "placeholder", "Male", "Short",
            R.drawable.model_sv_m, "https://en.wikipedia.org/wiki/Main_Page",
            "https://www.pinterest.com/")

    )

}


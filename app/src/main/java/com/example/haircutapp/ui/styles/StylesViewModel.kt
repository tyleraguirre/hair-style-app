package com.example.haircutapp.ui.styles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haircutapp.R
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import kotlinx.coroutines.*
import com.example.haircutapp.hairstylesdatabase.Hairstyle

class StylesViewModel(
    val database: HairstyleDao,
    application: Application) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _selectedStyle = MutableLiveData<Hairstyle?>()
    val selectedStyle: LiveData<Hairstyle?>
        get() = _selectedStyle

    private val _arrayOfStyles = MutableLiveData<List<Hairstyle?>>()
    val arrayOfStyles: LiveData<List<Hairstyle?>>
        get() = _arrayOfStyles


    fun navigationComplete() {
        _selectedStyle.value = null
    }

    private suspend fun insert(style: Hairstyle) {
        withContext(Dispatchers.IO) {
            database.insert(style)
        }
    }

    private suspend fun getStyleFromDataBase(): Hairstyle? {
        return withContext(Dispatchers.IO) {
            var style = database.get(0)
            style
        }
    }

    init {
//        onStartTracking(StylesObject.listOfHairStyles)
    }

    fun onStartTracking(data: List<Hairstyle>) {
        uiScope.launch {

           data.forEach { hairstyle ->
               insert(hairstyle)
           }

//            _selectedStyle.value = getStyleFromDataBase()
        }
    }

    fun setHairstyle(hairstyle: Hairstyle) {
        _selectedStyle.value = hairstyle
    }
}



package com.example.haircutapp.ui.styles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StylesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is styles Fragment"
    }
    val text: LiveData<String> = _text
}
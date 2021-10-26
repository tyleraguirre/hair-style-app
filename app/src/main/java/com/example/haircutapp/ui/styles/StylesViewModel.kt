package com.example.haircutapp.ui.styles

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.haircutapp.R
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import kotlinx.coroutines.*
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StylesViewModel(
    val database: HairstyleDao,
    application: Application) : ViewModel() {

//    private var viewModelJob = Job()

    private var allHairstylesList = mutableListOf<Hairstyle>()

    private var loopCount = 0

    companion object TAG {
        val TAG = "TAG"
    }


//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _selectedStyle = MutableLiveData<Hairstyle?>()
    val selectedStyle: LiveData<Hairstyle?>
        get() = _selectedStyle

//    private val _arrayOfStyles = MutableLiveData<List<Hairstyle?>>()
//    val arrayOfStyles: LiveData<List<Hairstyle?>>
//        get() = _arrayOfStyles

    init {
        fetchDataAndStore()
    }
    fun navigationComplete() {
        _selectedStyle.value = null
    }
//    private suspend fun insert(style: Hairstyle) {
//        withContext(Dispatchers.IO) {
//            database.insert(style)
//        }
//    }
//    private suspend fun getStyleFromDataBase(): Hairstyle? {
//        return withContext(Dispatchers.IO) {
//            var style = database.get(0)
//            style
//        }
//    }
    private lateinit var fbdatabase: DatabaseReference

    private val _hairstylesList = MutableLiveData<List<Hairstyle>>()
    val hairstylesList: LiveData<List<Hairstyle>>
        get() = _hairstylesList

    fun fetchDataAndStore() {

        fbdatabase =
            FirebaseDatabase.getInstance("https://hairstyle-api-e5fc7-default-rtdb.firebaseio.com/")
                .getReference("hairstyles")

        for (style in StyleList.styleList) {
            fbdatabase.child("$style").get().addOnSuccessListener { data ->
                val myObject = data.getValue(Hairstyle::class.java)
                val styleName = myObject?.styleName
                val imagesOfStyle = myObject?.imagesOfStyle
                val aboutStyle = myObject?.aboutStyle
                var hairstyle = Hairstyle(0, styleName!!, null, aboutStyle!!, imagesOfStyle!!)
                processData(hairstyle)
                loopCount += 1
                Log.i("${TAG.TAG}", "$loopCount")
            }
        }
    }
    fun processData(hairstyle: Hairstyle) {
        allHairstylesList.add(hairstyle)
        if (loopCount == StyleList.styleList.size - 1) {
    passToLiveData()
    loopCount = 0
}
    }
    fun passToLiveData() {
        _hairstylesList.value = allHairstylesList
    }
}

object StyleList {
    val styleList = listOf(
        "broflow",
        "buzzcut",
        "caesarcut",
        "combover",
        "crewcut",
        "fade",
        "fauxhawk",
        "fringe",
        "manbun",
        "pompadour",
        "quiff",
        "topknot",
        "undercut"
    )
}

//DataSnapshot { key = broflow, value = {aboutStyle=https://en.wikipedia.org/wiki/Wings_(haircut), styleName=Bro Flow, imagesOfStyle=https://www.pinterest.com/bartogilvie/bro-flow-hairstyles-men/} }




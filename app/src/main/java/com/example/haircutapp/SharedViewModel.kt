package com.example.haircutapp

import android.app.Application
import android.util.Log
import androidx.core.view.isInvisible
import androidx.lifecycle.*
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import com.example.haircutapp.hairstylesdatabase.HairstyleDao
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import com.example.haircutapp.ui.detail.DetailFragment
import com.example.haircutapp.ui.favorites.FavoritesFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.launch
import java.lang.Exception

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val dao = HairstyleDatabase.getInstance(application).HairstyleDao

    val hairstylesList: LiveData<List<Hairstyle>> = dao.getAllHairstyles()

    private val _selectedStyle = MutableLiveData<Hairstyle?>()
    val selectedStyle: LiveData<Hairstyle?>
        get() = _selectedStyle

    fun setHairstyle(hairstyle: Hairstyle) {
        _selectedStyle.value = hairstyle
    }

    fun isFavorited(): Boolean {
       if (_selectedStyle.value?.favorited == 1) {
           return true
       }
        return false
    }

     fun updateHairstyle() {
        viewModelScope.launch {
            var hairstyle = _selectedStyle.value
            hairstyle?.favorited = 1
            dao.update(hairstyle!!)
        }
    }
}

    /*This function gets FB reference and we pass in the styleList to the url to access our objects then pass
    it to our live data for the fragment
     */

//    private lateinit var fbdatabase: DatabaseReference

//    private var loopCount = 0

//    companion object TAG {
//        val TAG = "TAG"
//    }
//
//    fun fetchDataAndStore() {
//        viewModelScope.launch {
//            _hairstylesList.value = dao.getAllHairstyles()
//        }
//    }

//    fun fetchDataAndStore() {
//
//        fbdatabase =
//            FirebaseDatabase.getInstance("https://hairstyle-api-e5fc7-default-rtdb.firebaseio.com/")
//                .getReference("hairstyles")
//
//        for (style in StyleDataList.styleList) {
//            fbdatabase.child("$style").get().addOnSuccessListener { data ->
//                val myObject = data.getValue(Hairstyle::class.java)
//                var styleName = myObject?.styleName
//                var imagesOfStyle = myObject?.imagesOfStyle
//                var aboutStyle = myObject?.aboutStyle
//                var styleImages= StyleDataList.styleImageList
//                var hairstyle = Hairstyle(0, styleName!!, 0, styleImages[style], aboutStyle!!, imagesOfStyle!!)
//                processData(hairstyle)
//                loopCount += 1
//                Log.i("${TAG.TAG}", "$loopCount")
//            }
//        }
//    }
//    fun processData(hairstyle: Hairstyle) {
//        allHairstylesList.add(hairstyle)
//        if (loopCount == StyleDataList.styleList.size - 1) {
//    passToLiveData()
//    loopCount = 0
//}

//    fun passToLiveData() {
//        _hairstylesList.value = allHairstylesList
//    }
//}
//
//object StyleDataList {
//    val styleList = listOf(
//        "broflow",
//        "buzzcut",
//        "caesarcut",
//        "combover",
//        "crewcut",
//        "fade",
//        "fauxhawk",
//        "fringe",
//        "manbun",
//        "pompadour",
//        "quiff",
//        "topknot",
//        "undercut"
//    )
//    val styleImageList = mapOf(
//        "broflow" to R.drawable.model_sv_m,
//        "buzzcut" to R.drawable.buzzcut_sv_m,
//        "caesarcut" to R.drawable.model_sv_m,
//        "combover" to R.drawable.model_sv_m,
//        "crewcut" to R.drawable.model_sv_m,
//        "fade" to R.drawable.model_sv_m,
//        "fauxhawk" to R.drawable.fauxhawk_sv_m,
//        "fringe" to R.drawable.model_sv_m,
//        "manbun" to R.drawable.manbun_sv_m,
//        "pompadour" to R.drawable.pompadour_sv_m,
//        "quiff" to R.drawable.quiff_sv_m,
//        "topknot" to R.drawable.manbun_sv_m,
//        "undercut" to R.drawable.model_sv_m
//    )
//}


//DataSnapshot { key = broflow, value = {aboutStyle=https://en.wikipedia.org/wiki/Wings_(haircut), styleName=Bro Flow, imagesOfStyle=https://www.pinterest.com/bartogilvie/bro-flow-hairstyles-men/} }


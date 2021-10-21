package com.example.haircutapp.hairstylesdatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "hairstyle_table")
data class Hairstyle(

    @PrimaryKey(autoGenerate = true)
    var hairstyleId: Long = 0L,

    @ColumnInfo(name = "style_name")
    val styleName: String,

//    @ColumnInfo(name = "gender")
//    var gender: String,
//
//    @ColumnInfo(name = "style_length")
//    var styleLength: String,

    @ColumnInfo(name = "style_image")
    var styleImage: Int?,

    @ColumnInfo(name = "about_style")
    val aboutStyle: String,

    @ColumnInfo(name = "images_of_style")
    val imagesOfStyle: String

    ) : Parcelable {

//    companion object jsonSubmitter {
//        val jsonList = listOf<String>(
//            "broflow.json",
//            "buzzcut.json",
//            "caesarcut.json",
//            "combover.json",
//            "crewcut.json",
//            "fade.json",
//            "fauxhawk.json",
//            "fringe.json",
//            "manbun.json",
//            "pompadour.json",
//            "quiff.json",
//            "topknow.json",
//            "undercut.json"
//        )
//    }
}

//{ styles json
//    "BROFLOW": "Bro Flow",
//    "BUZZCUT": "Buzz Cut",
//    "CAESARCUT": "Caesar Cut",
//    "COMBOVER": "Comb Over",
//    "CREWCUT": "Crew Cut",
//    "FADE": "Fade",
//    "FAUXHAWK": "Faux Hawk",
//    "FRINGE": "Fringe",
//    "MANBUN": "Man Bun",
//    "POMPADOUR": "Pompadour",
//    "QUIFF": "Quiff",
//    "TOPKNOT": "Topknot",
//    "UNDERCUT": "Undercut"
//}

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
    var styleName: String = "",

    @ColumnInfo(name = "favorited")
    var favorited: Int = 0,
    
//    @ColumnInfo(name = "gender")
//    var gender: String,
//
//    @ColumnInfo(name = "style_length")
//    var styleLength: String,

    @ColumnInfo(name = "style_image")
    var styleImage: Int? = null,

    @ColumnInfo(name = "about_style")
    var aboutStyle: String = "",

    @ColumnInfo(name = "images_of_style")
    var imagesOfStyle: String = ""

    ) : Parcelable

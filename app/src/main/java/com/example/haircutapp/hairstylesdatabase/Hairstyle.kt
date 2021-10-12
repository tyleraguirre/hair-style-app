package com.example.haircutapp.hairstylesdatabase

import android.content.res.Resources
import android.os.Parcelable
import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize
import java.net.URL


@Parcelize
@Entity(tableName = "hairstyle_table")
data class Hairstyle(

    @PrimaryKey(autoGenerate = true)
    var hairstyleId: Long = 0L,

    @ColumnInfo(name = "style_name")
    val styleName: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "style_length")
    var styleLength: String,

    @ColumnInfo(name = "style_image")
    var styleImage: Int,

    @ColumnInfo(name = "about_style")
    val aboutStyle: String,

    @ColumnInfo(name = "images_of_style")
    val imagesOfStyle: String

    ) : Parcelable

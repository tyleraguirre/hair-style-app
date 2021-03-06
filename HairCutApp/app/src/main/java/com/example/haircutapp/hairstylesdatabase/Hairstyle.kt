package com.example.haircutapp.hairstylesdatabase

import android.content.res.Resources
import android.os.Parcelable
import android.widget.ImageView
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

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "style_length")
    var styleLength: String,

    @ColumnInfo(name = "style_image")
    var styleImage: Int
    ) : Parcelable

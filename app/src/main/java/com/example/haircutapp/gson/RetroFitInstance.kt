package com.example.haircutapp.gson

import android.util.Log
import com.example.haircutapp.hairstylesdatabase.HairstyleDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var database: DatabaseReference

object RetroFitInstance {
    val DATABASE_URL = "https://hairstyle-api-e5fc7-default-rtdb.firebaseio.com/"

    const val TAG = "RetroFitInstance"

//    val styles: String = ""

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(DATABASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: HairstyleApi by lazy {
        retrofit.create(HairstyleApi::class.java)
    }
     fun readData() {
        database = FirebaseDatabase.getInstance().getReference("hairstyle-api-e5fc7-default-rtdbfirebaseio")
        database.child("hairstyles").child("broflow").child("aboutStyle").get().addOnSuccessListener { data ->
            Log.i(TAG, "Here is the $data")
        }
    }
}
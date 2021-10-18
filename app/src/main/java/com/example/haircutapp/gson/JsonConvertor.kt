package com.example.haircutapp.gson

import com.example.haircutapp.hairstylesdatabase.Hairstyle
import retrofit2.Response
import retrofit2.http.GET


interface HairstyleApi {
    @GET("styles.json")
    suspend fun getStyles(): Response<List<Hairstyle>>

    @GET("styleinfo.json")
    suspend fun getStyleInfo(): Response<List<Hairstyle>>

    @GET("styleimages.json")
    suspend fun getStyleImages(): Response<List<Hairstyle>>
}





package com.example.haircutapp.gson

import com.example.haircutapp.hairstylesdatabase.Hairstyle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url



interface HairstyleApi {

    @GET("hairstyles")
    suspend fun getStyles(): Response<Hairstyle>
}





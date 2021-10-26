package com.example.haircutapp.gson

import com.example.haircutapp.gson.RetroFitInstance.api
import com.example.haircutapp.hairstylesdatabase.Hairstyle
import retrofit2.Response

class Repository {

    suspend fun getStyles(): Response<Hairstyle> {
            return RetroFitInstance.api.getStyles()
    }
}
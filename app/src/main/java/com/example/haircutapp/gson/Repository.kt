package com.example.haircutapp.gson

import com.example.haircutapp.hairstylesdatabase.Hairstyle
import retrofit2.Response

class Repository {

    suspend fun getStyles(): Response<List<Hairstyle>> {
        return RetroFitInstance.api.getStyles()
    }
    suspend fun getStyleInfo(): Response<List<Hairstyle>> {
        return RetroFitInstance.api.getStyleInfo()
    }
    suspend fun getStyleImages(): Response<List<Hairstyle>> {
        return RetroFitInstance.api.getStyleImages()
    }
}
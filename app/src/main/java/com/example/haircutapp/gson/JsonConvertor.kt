package com.example.haircutapp.gson

import com.example.haircutapp.gson.JsonConvertor.RetroFitInstance.DATABASE_URL
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class JsonConvertor {
    private val gson = Gson()
//    private val stylesJson

    companion object RetroFitInstance {

        val DATABASE_URL = "https://hairstyle-api-e5fc7-default-rtdb.firebaseio.com/"

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(DATABASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: JsonConvertor by lazy {
            retrofit.create(JsonConvertor::class.java)
        }
    }
}

interface HairstyleApi {
    @GET("styles")
    suspend fun getStyles(): Response<List<Styles>>

    @GET("styleinfo")
    suspend fun getStyleInfo(): Response<List<StylesInfo>>

    @GET("styleimages")
    suspend fun getStyleImages(): Response<List<StyleImages>>

}

class Repository {
    suspend fun getStyles(): Response<List<Styles>> {
        return JsonConvertor.api.getStyles()
    }
    suspend fun getStyleInfo(): Response<List<StylesInfo>> {
        return JsonConvertor.api.getStyleInfo()
    }
    suspend fun getStyleImages(): Response<List<StyleImages>> {
        return JsonConvertor.api.getStyleImages()
    }
}
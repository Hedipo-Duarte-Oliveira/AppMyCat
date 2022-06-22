package com.example.appcat.data.api

import com.example.appcat.model.Cat
import com.example.appcat.utils.Constants.Companion.BASE_URL
import com.example.appcat.utils.Constants.Companion.PATH_URL_CATS
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ICatClient {

    @GET(PATH_URL_CATS)
    fun getCatBreeds(
        @Query("apikey") apikey: String?
    ): Call<List<Cat>>

    companion object {
        private val retrofit: ICatClient by lazy {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ICatClient::class.java)
        }
        fun getBaseUrl(): ICatClient {
            return retrofit
        }
    }
}

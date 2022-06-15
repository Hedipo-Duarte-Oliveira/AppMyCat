package com.example.appcat.data.api

import com.example.appcat.model.Cat
import com.example.appcat.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ICatClient {

    @GET("v1/breeds?x-api-key=8a334d12-3aa2-4ad5-87a9-429d62a1d8b2")
    fun getCatBreeds(): Call<List<Cat>>

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val catService = retrofit.create(ICatClient::class.java)
    }
}

package com.example.appcat.data.repository

import com.example.appcat.data.api.ICatClient
import com.example.appcat.utils.Constants

class RepositoryCats(private val retrofit: ICatClient) {

    fun getBreedCats() = getCatsRepository()

    private fun getCatsRepository() = retrofit.getCatBreeds(Constants.API_KEY)
}

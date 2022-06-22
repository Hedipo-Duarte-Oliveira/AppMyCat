package com.example.appcat.usecase

import com.example.appcat.data.repository.RepositoryCats
import com.example.appcat.model.Cat
import retrofit2.await

class UseCaseCats(private val repositoryCats: RepositoryCats) {

    suspend fun getListCat(): List<Cat> {
        return getListCats()
    }

    private suspend fun getListCats(): List<Cat> {
        return repositoryCats.getBreedCats().await()
    }
}

package com.example.appcat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcat.data.api.ICatClient
import com.example.appcat.databinding.ActivityMainBinding
import com.example.appcat.model.Cat
import com.example.appcat.model.CatItemAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val catListAdpater = CatItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getBreedsComCourotinas()

        binding.recicleviewCats.adapter = catListAdpater
    }

    private fun getBreedsComCourotinas() {
        var catList: List<Cat>
        CoroutineScope(Dispatchers.IO).launch {
            catList = ICatClient.catService.getCatBreeds().await()
            withContext(Dispatchers.Main) {
                catListAdpater.submitList(catList)
            }
        }
    }

    private fun getBreedsSemCourotines() {
        ICatClient.catService.getCatBreeds().enqueue(
            object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    val response = response.body()
                    catListAdpater.submitList(response)
                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    println("Falhou")
                }
            })
    }
}

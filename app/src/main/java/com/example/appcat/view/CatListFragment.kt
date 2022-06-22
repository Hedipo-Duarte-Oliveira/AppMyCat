package com.example.appcat.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcat.R
import com.example.appcat.ViewModel.CatListViewModel
import com.example.appcat.data.api.ICatClient
import com.example.appcat.data.repository.RepositoryCats
import com.example.appcat.databinding.FragmentCoinListBinding
import com.example.appcat.exceptions.ResultWrapper
import com.example.appcat.model.Cat
import com.example.appcat.model.CatAdapter
import com.example.appcat.usecase.UseCaseCats

class CoinListFragment : Fragment(R.layout.fragment_coin_list) {

    private lateinit var binding: FragmentCoinListBinding
    private lateinit var viewModel: CatListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCoinListBinding.bind(view)

        viewModel = ViewModelProvider(
            this,
            CatListViewModel.CatListViewModelFactory(
                UseCaseCats(RepositoryCats(ICatClient.getBaseUrl())),
            )
        )[CatListViewModel::class.java]

        setupObservers()
        requestApi()
    }

    private fun setupView(listcat: List<Cat>?) {
        val adapter = CatAdapter()
        binding.recicleviewCats.layoutManager = LinearLayoutManager(context)
        binding.recicleviewCats.setHasFixedSize(true)
        adapter.submitList(viewModel.listCatAdapter.value)
        binding.recicleviewCats.adapter = adapter
    }

    private fun requestApi() {
        viewModel.requestApiCat()
    }

    private fun setupObservers() {
        viewModel.errrMsg.observe(viewLifecycleOwner) { resultError ->
            setupErro(resultError)
        }

        viewModel.listCatAdapter.observe(viewLifecycleOwner) {
            setupView(it)
        }
    }

    private fun setupErro(resultError: ResultWrapper<String>) {
        when (resultError) {
            is ResultWrapper.Error -> {
            }
        }
    }
}

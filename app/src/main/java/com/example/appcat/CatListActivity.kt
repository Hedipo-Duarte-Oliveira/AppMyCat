package com.example.appcat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appcat.ViewModel.CatListViewModel
import com.example.appcat.data.api.ICatClient
import com.example.appcat.data.repository.RepositoryCats
import com.example.appcat.databinding.ActivityMainBinding
import com.example.appcat.usecase.UseCaseCats
import kotlinx.coroutines.flow.combine

class CatListActivity : AppCompatActivity() {

    private val viewModel: CatListViewModel by viewModels {
        CatListViewModel.CatListViewModelFactory(UseCaseCats(RepositoryCats(ICatClient.getBaseUrl())))
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObserverFragment()
        setupContainerFragments()
    }

    private fun setupObserverFragment() {
        viewModel.listcats.observe(this) {
        }
        viewModel.errrMsg.observe(this) {
        }
    }

    private fun setupContainerFragments() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.apply { setupWithNavController(navController) }
    }
}

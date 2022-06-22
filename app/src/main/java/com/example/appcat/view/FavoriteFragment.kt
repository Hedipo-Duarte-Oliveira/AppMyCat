package com.example.appcat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appcat.R
import com.example.appcat.databinding.FavoriteFragmentBinding

class FavoriteFragment : Fragment(R.layout.favorite_fragment) {

    private lateinit var binding: FavoriteFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FavoriteFragmentBinding.bind(view)
    }
}

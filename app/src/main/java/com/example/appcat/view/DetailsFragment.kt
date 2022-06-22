package com.example.appcat.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appcat.R
import com.example.appcat.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private lateinit var binding: DetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DetailsFragmentBinding.bind(view)
    }
}

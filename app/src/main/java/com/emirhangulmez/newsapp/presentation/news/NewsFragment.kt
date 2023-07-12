package com.emirhangulmez.newsapp.presentation.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.emirhangulmez.newsapp.R
import com.emirhangulmez.newsapp.common.viewBinding
import com.emirhangulmez.newsapp.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding by viewBinding(FragmentNewsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }
}
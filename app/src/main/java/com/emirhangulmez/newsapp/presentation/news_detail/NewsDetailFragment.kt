package com.emirhangulmez.newsapp.presentation.news_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.emirhangulmez.newsapp.R
import com.emirhangulmez.newsapp.common.viewBinding
import com.emirhangulmez.newsapp.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val binding by viewBinding(FragmentNewsDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

        }
    }
}
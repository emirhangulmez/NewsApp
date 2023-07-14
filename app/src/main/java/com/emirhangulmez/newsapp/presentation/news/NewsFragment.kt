package com.emirhangulmez.newsapp.presentation.news

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.emirhangulmez.newsapp.R
import com.emirhangulmez.newsapp.common.viewBinding
import com.emirhangulmez.newsapp.databinding.FragmentNewsBinding
import com.emirhangulmez.newsapp.presentation.news.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding by viewBinding(FragmentNewsBinding::bind)
    private val viewModel by viewModels<NewsViewModel>()
    private val adapter by lazy {
        NewsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel.getTopHeadlines()
            newsRv.adapter = adapter

            swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(
                    requireContext(), R.color.primary_500
                )
            )

            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
            }
            lifecycleScope.launch {
                adapter.loadStateFlow.collect { loadStates ->
                    swipeRefresh.isRefreshing =
                        loadStates.refresh is LoadState.Loading || loadStates.append is LoadState.Loading
                }
            }

            viewModel.topHeadlines.observe(viewLifecycleOwner) { pagingData ->
                lifecycleScope.launch {
                    adapter.submitData(pagingData)
                }
            }
        }
    }
}
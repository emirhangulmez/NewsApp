package com.emirhangulmez.newsapp.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirhangulmez.newsapp.domain.entity.ArticleEntity
import com.emirhangulmez.newsapp.domain.usecase.TopHeadlinesPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val topHeadlinesPagerUseCase: TopHeadlinesPagerUseCase
) : ViewModel() {

    private val _topHeadlines = MutableLiveData<PagingData<ArticleEntity>>()
    val topHeadlines: LiveData<PagingData<ArticleEntity>> = _topHeadlines

    init {
        getTopHeadlines()
    }

    private fun getTopHeadlines() = viewModelScope.launch {
        topHeadlinesPagerUseCase().cachedIn(viewModelScope).collect { pagingData ->
            _topHeadlines.postValue(pagingData)
        }
    }
}